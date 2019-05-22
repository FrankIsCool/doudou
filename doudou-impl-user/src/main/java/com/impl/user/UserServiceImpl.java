package com.impl.user;

import com.alibaba.dubbo.config.annotation.Service;
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

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    @Override
    public JsonResult<Integer> saveUser(UserNode userNode) {
        JsonResult<Integer> result = new JsonResult<>();
        userNode = initUserNode(userNode);
        userNode = userRepository.save(userNode);
        if(null==userNode.getId()||userNode.getId()<0){
            return JsonResult.error(ExceptionCode.ERRO_101000);
        }
        result.setData(1);
        return result;
    }

    @Override
    public JsonResult<UserNode> getUser(long id) {
        JsonResult<UserNode> result = new JsonResult<>();
        result.setData(userRepository.getUserById(id));
        return result;
    }
    @Override
    public JsonResult<UserNode> getUser(String userCode) {
        JsonResult<UserNode> result = new JsonResult<UserNode>();
        result.setData(userRepository.getUserByCode(userCode));
        return result;
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
        JsonResult<UserLogin> result = new JsonResult<>();
        UserLogin userLogin = new UserLogin();
        userLogin.setToken(token);
        userLogin.setUserName(userName);
        userLogin.setUserCode(userNode.getUserCode());
        boolean set = redisService.set(userNode.getUserCode(), userLogin);
        if(!set){
            return JsonResult.error(ExceptionCode.ERRO_102001);
        }
        //添加登录记录
        UserLoginLog userLoginLog  = new UserLoginLog();
        userLoginLog.setUserId(userNode.getId());
        userLoginLog.setSource(Integer.valueOf(source));
        userLoginLogMapper.save(userLoginLog);

        result.setData(userLogin);
        return result;
    }

    /**
     * 初始化用户信息
     * @param userNode 用户节点
     * @return 用户节点
     */
    private UserNode initUserNode(UserNode userNode){
        userNode.setUserCode(UUID.randomUUID().toString().replace("-",""));
        userNode.setState(UserNode.USER_STATE_NORMAL);
        return userNode;
    }
}
