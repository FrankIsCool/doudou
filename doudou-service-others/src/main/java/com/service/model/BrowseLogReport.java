package com.service.model;

import com.alibaba.fastjson.JSONObject;
import com.common.empty.EmptyUtil;

import java.sql.Date;
import java.util.List;

public class BrowseLogReport {
    //id
    private long id;
    //访问次数
    private int num;
    //统计时间
    private String reportTime;
    //用户访问次数
    private int userNum;
    //访问用户id集合，json字符串
    private String userIds;
    //访问用户id集合
    private List<Long> userIdsVo;
    //被访问的功能次数
    private int urlNum;
    //访问功能url地址，json字符串
    private String urls;
    //访问功能url地址
    private List<String> urlsVo;
    //创建时间
    private Date createTime;

    public List<Long> getUserIdsVo() {
        return userIdsVo;
    }

    public void setUserIdsVo(List<Long> userIdsVo) {
        if(EmptyUtil.isNotEmpty(userIdsVo)){
            this.userIds = JSONObject.toJSONString(userIdsVo);
            this.userIdsVo = userIdsVo;
        }
    }

    public List<String> getUrlsVo() {
        return urlsVo;
    }

    public void setUrlsVo(List<String> urlsVo) {
        if(EmptyUtil.isNotEmpty(urlsVo)){
            this.urls = JSONObject.toJSONString(urlsVo);
            this.urlsVo = urlsVo;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getUrlNum() {
        return urlNum;
    }

    public void setUrlNum(int urlNum) {
        this.urlNum = urlNum;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        if(EmptyUtil.isNotEmpty(userIds)){
            this.userIdsVo = JSONObject.parseArray(urls,Long.class);
            this.userIds = userIds;
        }
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        if(EmptyUtil.isNotEmpty(urls)){
            this.urlsVo = JSONObject.parseArray(urls,String.class);
            this.urls = urls;
        }
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
