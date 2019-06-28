package com.impl.user;

import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.common.token.TokenUtil;
import com.common.token.TokenVo;
import com.impl.dao.slaver.user.UserLoginLogMapper;
import com.impl.model.UserLoginLog;
import com.impl.repository.UserRepository;
import com.service.model.UserLogin;
import com.service.model.UserNode;
import com.service.redis.RedisService;
import com.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@com.alibaba.dubbo.config.annotation.Service
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    @Override
    public JsonResult<Integer> saveUser(UserNode userNode) {
        userNode = initUserNode(userNode);
        userRepository.saveUser(userNode.getUserCode(), userNode.getUserName(), userNode.getPassword(), userNode.getState());
        return JsonResult.success();
    }

    @Override
    public JsonResult<UserNode> getUser(long id) {
        return JsonResult.success(userRepository.getUserById(id));
    }
    @Override
    public JsonResult<UserNode> getUser(String userCode) {
        return JsonResult.success(userRepository.getUserByCode(userCode));
    }
    @Override
    public JsonResult<UserLogin> userLogin(String userName, String password, String source) {
        List<UserNode> userNodes = userRepository.getUserByName(userName);
        if(null==userNodes||userNodes.size()<0){
            return JsonResult.error(ExceptionCode.ERRO_102002);
        }
        if(userNodes.size()>1){
            return JsonResult.error(ExceptionCode.ERRO_102003);
        }
        UserNode userNode = userNodes.get(0);
        if(!password.equals(userNode.getPassword())){
            return JsonResult.error(ExceptionCode.ERRO_102004);
        }
        if(EmptyUtil.isEmpty(userNode.getState())){
            userNode.setState(UserNode.USER_STATE_NORMAL);
        }
        if(userNode.getState()==UserNode.USER_STATE_ABNORMAL){
            return JsonResult.error(ExceptionCode.ERRO_102003);
        }
        if(userNode.getState()==UserNode.USER_STATE_FREEZE){
            return JsonResult.error(ExceptionCode.ERRO_102005);
        }
        TokenVo tokenVo = new TokenVo();
        tokenVo.setUserCode(userNode.getUserCode());
        tokenVo.setUserId(String.valueOf(userNode.getId()));
        tokenVo.setSource(source);
        String token = TokenUtil.createToken(tokenVo);
        UserLogin userLogin = new UserLogin();
        userLogin.setToken(token);
        userLogin.setUserName(userName);
        userLogin.setUserCode(userNode.getUserCode());
        userLogin.setState(userNode.getState());
        boolean set = redisService.set(userNode.getUserCode(), userLogin);
        if(!set){
            return JsonResult.error(ExceptionCode.ERRO_102001);
        }
        //添加登录记录
        UserLoginLog userLoginLog  = new UserLoginLog();
        userLoginLog.setUserId(userNode.getId());
        userLoginLog.setSource(Integer.valueOf(source));
        userLoginLogMapper.save(userLoginLog);

        return JsonResult.success(userLogin);
    }

    @Override
    public JsonResult<List<UserNode>> getUserAll() {
        return JsonResult.success(userRepository.getUserNodeList());
    }

    @Override
    public JsonResult<List<UserNode>> getUsers(List<Long> ids) {
        if(EmptyUtil.isEmpty(ids)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        List<UserNode> userNodes = new ArrayList<>();
        for(long id : ids){
            userNodes.add(userRepository.getUserById(id));
        }
        return JsonResult.success(userNodes);
    }

    /**
     * 初始化用户信息
     * @param userNode  用户节点
     * @return          用户节点
     */
    private UserNode initUserNode(UserNode userNode){
        userNode.setUserCode(UUID.randomUUID().toString().replace("-",""));
        userNode.setState(UserNode.USER_STATE_NORMAL);
        return userNode;
    }
}
