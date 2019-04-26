package com.common.token;

import com.common.empty.EmptyUtil;
import com.common.jwt.JWTUtil;
import com.common.object.ObjectUtil;

import java.util.Map;

public class TokenUtil {

    /**
     * 创建用户登录令牌
     * @param token		登录加密实体类
     * @return 登录令牌
     */
    public static String createToken(Token token){
        if(EmptyUtil.isEmpty(token)){
            return null;
        }
        Map<String, Object> tokenMap = ObjectUtil.toMap(token);
        return JWTUtil.encryptionToken(tokenMap);
    }
    /**
     * 验证登录token
     * @param token		登录令牌
     * @param source	登录源
     * @return 加密串是否正确
     */
    public static boolean loginVerifyToken(String token,String source){
        if(EmptyUtil.isEmpty(token,source)){
            return false;
        }
        Map<String, String> condition = JWTUtil.getCondition(token);
        if(EmptyUtil.isEmpty(condition)){
            return false;
        }
        if(!condition.containsKey("source")){
            return false;
        }
        String sourceToken = condition.get("source");
        if(!sourceToken.equals(source)){
            return false;
        }
        return true;
    }

}
