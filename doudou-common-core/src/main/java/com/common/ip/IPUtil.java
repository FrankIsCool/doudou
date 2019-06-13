package com.common.ip;

import com.common.empty.EmptyUtil;
import com.common.ip.ali.*;
import com.common.ip.bj.BJData;
import com.common.ip.bj.BJIPUtil;
import com.common.ip.bj.BJResult;
import com.common.ip.cha.ChaData;
import com.common.ip.cha.ChaIPUtil;
import com.common.ip.cha.ChaResult;
import com.common.ip.free.FreeAddress;
import com.common.ip.free.FreeIPUtil;
import com.common.ip.ipip.IPIPData;
import com.common.ip.ipip.IPIPUtil;
import com.common.ip.juhe.JHIPData;
import com.common.ip.juhe.JHIPUtil;
import com.common.weather.juhe.JHResult;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtil {
    /**
     * 根据ip获取地址
     * @param ip
     * @return
     */
    public static IPAddressVo getAddress(String ip){
        if(EmptyUtil.isEmpty(ip)){
            return null;
        }
        IPAddressVo ipAddressVo = new IPAddressVo();
        ipAddressVo.setIp(ip);
        FSIPResult addressByFS = AliIPUtil.getAddressByFS(ip);
        if(EmptyUtil.isNotEmpty(addressByFS)&&EmptyUtil.isNotEmpty(addressByFS.getData())){
            FSIPData ipData = addressByFS.getData();
            ipAddressVo.setCountry(ipData.getCountry());
            ipAddressVo.setProvince(ipData.getProv());
            ipAddressVo.setCity(ipData.getCity());
            ipAddressVo.setIsp(ipData.getIsp());
            return ipAddressVo;
        }
//        GDIPResult addressByGD = AliIPUtil.getAddressByGD(ip);
//        if(EmptyUtil.isNotEmpty(addressByGD)){
//            ipAddressVo.setCountry(addressByGD.get());
//            ipAddressVo.setProvince(addressByGD.getProvince());
//            ipAddressVo.setCity(addressByGD.getCity());
//            ipAddressVo.setIsp(addressByGD.getInfocode());
//            return ipAddressVo;
//        }
        HCIPResult addressByHC = AliIPUtil.getAddressByHC(ip);
        if(EmptyUtil.isNotEmpty(addressByHC)&&EmptyUtil.isNotEmpty(addressByHC.getData())){
            HCIPData ipData = addressByHC.getData();
            ipAddressVo.setCountry(ipData.getCountry());
            ipAddressVo.setProvince(ipData.getRegion());
            ipAddressVo.setCity(ipData.getCity());
            ipAddressVo.setIsp(ipData.getIsp());
            return ipAddressVo;
        }
        YYIPResult addressByYY = AliIPUtil.getAddressByYY(ip);
        if(EmptyUtil.isNotEmpty(addressByYY)&&EmptyUtil.isNotEmpty(addressByYY.getShowapiResBody())){
            YYIPData ipData = addressByYY.getShowapiResBody();
            ipAddressVo.setCountry(ipData.getCountry());
            ipAddressVo.setProvince(ipData.getRegion());
            ipAddressVo.setCity(ipData.getCity());
            ipAddressVo.setIsp(ipData.getIsp());
            return ipAddressVo;
        }
        BJResult bjResult = BJIPUtil.getAdress(ip);
        if(EmptyUtil.isNotEmpty(bjResult)&&EmptyUtil.isNotEmpty(bjResult.getData())){
            BJData ipData = bjResult.getData();
            ipAddressVo.setCountry(ipData.getCountry());
            ipAddressVo.setProvince(ipData.getProvince());
            ipAddressVo.setCity(ipData.getCity());
            ipAddressVo.setIsp(ipData.getIsp());
            return ipAddressVo;
        }
        ChaResult chaResult = ChaIPUtil.getAdress(ip);
        if(EmptyUtil.isNotEmpty(chaResult)&&EmptyUtil.isNotEmpty(chaResult.getData())){
            ChaData ipData = chaResult.getData();
            ipAddressVo.setCountry(ipData.getCountry());
            ipAddressVo.setProvince(ipData.getProvince());
            ipAddressVo.setCity(ipData.getCity());
            ipAddressVo.setIsp(ipData.getLinetype());
            return ipAddressVo;
        }
        FreeAddress freeAddress = FreeIPUtil.getAdress(ip);
        if(EmptyUtil.isNotEmpty(freeAddress)){
            ipAddressVo.setCountry(freeAddress.getCountry());
            ipAddressVo.setProvince(freeAddress.getProvince());
            ipAddressVo.setCity(freeAddress.getCity());
            ipAddressVo.setIsp(freeAddress.getIsp());
            return ipAddressVo;
        }
        IPIPData ipipData = IPIPUtil.getAdress(ip);
        if(EmptyUtil.isNotEmpty(ipipData)){
            ipAddressVo.setCountry(ipipData.getCountry());
            ipAddressVo.setProvince(ipipData.getProvince());
            ipAddressVo.setCity(ipipData.getCity());
            ipAddressVo.setIsp(ipipData.getIsp());
            return ipAddressVo;
        }
        JHResult<JHIPData> jhResult = JHIPUtil.getAddress(ip);
        if(EmptyUtil.isNotEmpty(jhResult)&&EmptyUtil.isNotEmpty(jhResult.getResult())){
            JHIPData ipData = jhResult.getResult();
            ipAddressVo.setCountry(ipData.getCountry());
            ipAddressVo.setProvince(ipData.getProvince());
            ipAddressVo.setCity(ipData.getCity());
            ipAddressVo.setIsp(ipData.getIsp());
            return ipAddressVo;
        }

        return null;
    }

    /**
     * 获取ip地址
     * @param request
     * @return
     */
    public static IPAddressVo getAddress(HttpServletRequest request){
        String ipAddr = getIpAddr(request);
        if(EmptyUtil.isEmpty(ipAddr)){
            return null;
        }
        return getAddress(ipAddr);
    }
    /**
     * 获取当前网络ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
