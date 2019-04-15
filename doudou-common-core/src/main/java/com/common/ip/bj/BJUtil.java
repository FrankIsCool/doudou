package com.common.ip.bj;

import com.alibaba.fastjson.JSONObject;
import com.common.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class BJUtil {
    private static String URL = "http://api.help.bj.cn/apis/ip";
    /**
     * ip地址识别
     * @param ip ip
     * @return
     */
    public static BJResult getAdress(String ip){
        Map params = new HashMap();
        params.put("ip",ip);
        String s = HttpUtil.doGet(URL,params);
        BJResult data = JSONObject.parseObject(s,BJResult.class);
        return data;
    }

    public static void main(String[] args) {
        getAdress("113.132.171.119");
    }
}
