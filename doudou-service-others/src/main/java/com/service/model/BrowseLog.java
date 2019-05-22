package com.service.model;

import java.io.Serializable;
import java.util.Date;

public class BrowseLog implements Serializable {

    private static final long serialVersionUID = -1923645274767028479L;
    //id
    private int id;
    //访问url
    private String url;
    //用户id
    private int userId;
    //来源
    private int source;
    //其他信息，以json的形式存储
    private String otherInfo;
    //用户访问设备的信息，以json的形式存储
    private String deviceInfo;
    //创建时间
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
