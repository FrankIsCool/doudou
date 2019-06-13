package com.common.token;

/**
 * 用户登录令牌生成的内容实体类
 */
public class TokenVo {
    //用户id
    private String userId;
    //登录源
    private String source;
    //用户唯一code
    private String userCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
