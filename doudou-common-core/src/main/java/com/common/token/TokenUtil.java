package com.common.token;

import com.alibaba.fastjson.JSON;
import com.common.empty.EmptyUtil;
import com.common.jwt.JWTUtil;
import com.common.object.ObjectUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class TokenUtil {

    /**
     * 创建用户登录令牌
     * @param token		登录加密实体类
     * @return 登录令牌
     */
    public static String createToken(TokenVo token){
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
        TokenVo tokenVo = getTokenVo(token);
        if(EmptyUtil.isEmpty(tokenVo)){
            return false;
        }
        if(EmptyUtil.isEmpty(tokenVo.getSource())){
            return false;
        }
        if(!tokenVo.getSource().equals(source)){
            return false;
        }
        return true;
    }
    public static TokenVo getTokenVo(HttpServletRequest request){
        String token = request.getHeader("token");
        if(EmptyUtil.isEmpty(token)){
            return null;
        }
        return getTokenVo(token);
    }
    /**
     * token字符串转tokenvo实体类
     * @param token token加密串
     * @return      解密后的实体
     */
    public static TokenVo getTokenVo(String token){
        if(EmptyUtil.isEmpty(token)){
            return null;
        }
        Map<String, String> condition = JWTUtil.getCondition(token);
        if(EmptyUtil.isEmpty(condition)){
            return null;
        }
        TokenVo tokenVo = JSON.parseObject(JSON.toJSONString(condition), TokenVo.class);
        if(EmptyUtil.isEmpty(tokenVo)){
            return null;
        }
        return tokenVo;
    }
}
