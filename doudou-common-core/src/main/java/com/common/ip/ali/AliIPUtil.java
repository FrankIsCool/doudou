package com.common.ip.ali;

import com.alibaba.fastjson.JSONObject;
import com.common.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class AliIPUtil {

    private static String APPKEY = "24783367";
    private static String APPSECRET = "6a14b6545a178d2863ec2e8f91f85257";
    private static String APPCODE = "eaa0843bd1ea43c1879707497d0628d2";

    /*易源*/
    private static String YY_URL = "http://saip.market.alicloudapi.com/ip";
    /**
     * 易源--2000
     * @param ip
     * @return
     */
    public static YYIPResult getAddressByYY(String ip){

        Map headers = new HashMap();
        headers.put("Authorization","APPCODE "+APPCODE);

        Map params = new HashMap();
        params.put("ip",ip);
        String s = HttpUtil.doGet(YY_URL,headers, params);
        YYIPResult aliIPResult = JSONObject.parseObject(s, YYIPResult.class);
        return aliIPResult;
    }
    /*河南复数*/
    private static String FS_URL = "http://ipquery.market.alicloudapi.com/query";
    /**
     * 河南复数-100000
     * @param ip
     * @return
     */
    public static FSIPResult getAddressByFS(String ip){

        Map headers = new HashMap();
        headers.put("Authorization","APPCODE "+APPCODE);

        Map params = new HashMap();
        params.put("ip",ip);
        String s = HttpUtil.doGet(FS_URL,headers, params);
        FSIPResult fsipResult = JSONObject.parseObject(s, FSIPResult.class);
        return fsipResult;
    }
    /*华辰*/
    private static String HC_URL = "http://api01.aliyun.venuscn.com/ip";
    /**
     * 华辰-1000
     * @param ip
     * @return
     */
    public static HCIPResult getAddressByHC(String ip){

        Map headers = new HashMap();
        headers.put("Authorization","APPCODE "+APPCODE);

        Map params = new HashMap();
        params.put("ip",ip);
        String s = HttpUtil.doGet(HC_URL,headers, params);
        HCIPResult hcipResult = JSONObject.parseObject(s, HCIPResult.class);
        return hcipResult;
    }
    /*高德*/
    private static String GD_URL = "http://iploc.market.alicloudapi.com/v3/ip";
    /**
     * 高德-100000
     * @param ip
     * @return
     */
    public static GDIPResult getAddressByGD(String ip){

        Map headers = new HashMap();
        headers.put("Authorization","APPCODE "+APPCODE);

        Map params = new HashMap();
        params.put("ip",ip);
        String s = HttpUtil.doGet(GD_URL,headers, params);
        GDIPResult gdipResult = JSONObject.parseObject(s, GDIPResult.class);
        return gdipResult;
    }

}
