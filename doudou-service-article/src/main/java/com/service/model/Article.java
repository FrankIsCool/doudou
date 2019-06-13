package com.service.model;

import com.alibaba.fastjson.JSONObject;
import com.common.empty.EmptyUtil;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Article implements Serializable {

    //id
    private long id;
    //作者id
    private long userId;
    //标题
    private String title;
    //内容
    private String content;
    //浏览次数
    private int browseNum;
    //点赞次数
    private int likeNum;
    //标签字符串
    private String labels;
    //创建时间
    private Date createTime;
//============================================================
    //标签字符串
    private List<Label> labelsVo;
    //作者名
    private String userName;
    //是否点赞
    private boolean isLike;

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        if(EmptyUtil.isNotEmpty(labels)){
            this.labelsVo = JSONObject.parseArray(labels, Label.class);
            this.labels = labels;
        }
    }

    public List<Label> getLabelsVo() {
        return labelsVo;
    }

    public void setLabelsVo(List<Label> labelsVo) {
        if(EmptyUtil.isNotEmpty(labelsVo)){
            this.labels = JSONObject.toJSONString(labelsVo);
            this.labelsVo = labelsVo;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(int browseNum) {
        this.browseNum = browseNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
