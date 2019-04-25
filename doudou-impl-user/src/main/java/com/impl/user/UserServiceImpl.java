package com.impl.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.common.redis.RedisClientTemplate;
import com.impl.repository.UserRepository;
import com.service.model.UserLogin;
import com.service.model.UserNode;
import com.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisClientTemplate redisClientTemplate;
    public JsonResult<Integer> saveUser(UserNode userNode) {
        JsonResult<Integer> result = new JsonResult<Integer>();
        userNode = initUserNode(userNode);
        userNode = userRepository.save(userNode);
        if(null==userNode.getId()||userNode.getId()<0){
            return JsonResult.error(ExceptionCode.ERRO_101000);
        }
        result.setData(1);
        return result;
    }


    public JsonResult<UserNode> getUser(long id) {
        JsonResult<UserNode> result = new JsonResult<>();
//        Map map = userRepository.getUserById(id);
//        UserNode userNode = JSONObject.parseObject(JSONObject.toJSON(map).toString(), UserNode.class);
        result.setData(userRepository.getUserById(id));
        return result;
    }

    public JsonResult<UserNode> getUser(String userCode) {
        JsonResult<UserNode> result = new JsonResult<UserNode>();
//        UserNode userNode = userRepository.(id);
//        result.setData(nodeToUser(userNode));
        return result;
    }

    public JsonResult<UserLogin> userLogin(String userName, String password) {
        List<UserNode> userNodes = userRepository.getUserByName(userName);
        if(null==userNodes||userNodes.size()<0){
            return JsonResult.error(ExceptionCode.ERRO_101002);
        }
        if(userNodes.size()>1){
            return JsonResult.error(ExceptionCode.ERRO_101003);
        }
        UserNode userNode = userNodes.get(0);
        if(!password.equals(userNode.getPassword())){
            return JsonResult.error(ExceptionCode.ERRO_101004);
        }
        JsonResult<UserLogin> result = new JsonResult<UserLogin>();
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName(userName);
        redisClientTemplate.set(userName,userName);
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
//        user.setId(1L);
        return userNode;
    }
}
