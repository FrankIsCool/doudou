package com.service.model;

import java.sql.Date;

public class ArticleLikeLog {

    /**
     * 点赞
     */
    public static final int  STATE_NORMAL= 1;
    /**
     * 取消点赞
     */
    public static final int  STATE_CANCEL= 2;

    //id
    private long id;
    //浏览用户id
    private long userId;
    //文章id
    private long articleId;
    //登录源
    private int source;
    //1:点赞  2取消
    private int state;
    //登录源
    private Date updateTime;
    //创建时间
    private Date createTime;

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

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
