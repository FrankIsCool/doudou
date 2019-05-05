package com.common.exception;

public enum  ExceptionCode {

    //  系统级别异常
    ERRO_400("400","该请求中的语法错误。"),
    ERRO_401("401","该请求需要用户认证。"),
    ERRO_402("402","保留/以后使用。"),
    ERRO_403("403","该请求被拒绝。"),
    ERRO_404("404","暂无该接口。"),
    ERRO_405("405","该请求中的语法错误。"),
    ERRO_406("406","浏览器无法打开正在寻找的资源。"),
    ERRO_407("407","该请求需要用户认证。"),
    ERRO_408("408","该请求暂无返回结果。"),
    ERRO_410("410","暂无该接口。"),
    ERRO_413("413","该请求的实体超过了服务器要求。"),
    ERRO_414("414","URI超过了服务器能够处理的长度。"),
    ERRO_415("415","该请求的消息体的格式暂不支持。"),
    ERRO_416("416","暂不支持的URI方案。"),

    ERRO_500("500","接口异常,已反馈平台。"),


    ERRO_100000("100000","请求参数不能为空。"),
    ERRO_100001("100001","请求头中的请求源，不能为空。"),
    ERRO_100002("100002","请求的登录令牌不能为空。"),
    ERRO_100003("100003","请求的登录令牌错误。"),
    ERRO_100004("100004","请求参数格式错误。"),
    //  用户-注册异常
    ERRO_101000("101000","注册失败！信息已反馈平台。"),
    ERRO_101001("101001","注册失败！密码不一致。"),
    //用户-登陆异常
    ERRO_102001("101001","登录失败！信息已反馈平台。"),
    ERRO_102002("101002","登录失败！账号不存在。"),
    ERRO_102003("101003","登录失败！账号异常，请联系管理员。"),
    ERRO_102004("101004","登录失败！密码错误。"),
    ERRO_102005("101005","登录失败！账户已被冻结。"),

    ;


    private String code;
    private String msg;
    ExceptionCode(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // 普通方法
    public static String getMsg(String code) {
        for (ExceptionCode c : ExceptionCode.values()) {
            if (c.getCode() == code) {
                return c.msg;
            }
        }
        return null;
    }
}
