package com.common.news.juhe;

import com.alibaba.fastjson.JSONObject;
import com.common.http.HttpUtil;
import com.common.weather.juhe.JHResult;

import java.util.HashMap;
import java.util.Map;

public class JHNewsUtil {

    private static String URL = "http://v.juhe.cn/toutiao/index";

    private static String KEY = "45f0b48e813f99bdd25f1aada2a9c0cd";
    /**
     * 获取新闻
     * @param type  类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     * @return
     */
    public static JHResult getNews(String type){
        Map params = new HashMap();
        params.put("type",type);
        params.put("key",KEY);
        String s = HttpUtil.doGet(URL, params);
        JHResult jhResult = JSONObject.parseObject(s, JHResult.class);
        JHNewsResult jhNewsResult = JSONObject.parseObject(jhResult.getResult().toString(), JHNewsResult.class);
        jhResult.setResult(jhNewsResult);
        return jhResult;
    }
}
