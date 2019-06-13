package com.common.news.juhe;

import com.alibaba.fastjson.annotation.JSONField;

public class JHNewsData {
    @JSONField(name = "uniquekey")
    private String uniquekey;
    @JSONField(name = "title")
    private String title;
    @JSONField(name = "date")
    private String date;
    @JSONField(name = "category")
    private String category;
    @JSONField(name = "author_name")
    private String authorName;
    @JSONField(name = "url")
    private String url;
    @JSONField(name = "thumbnail_pic_s")
    private String thumbnailPicS;
    @JSONField(name = "thumbnail_pic_s02")
    private String thumbnailPicS02;
    @JSONField(name = "thumbnail_pic_s03")
    private String thumbnailPicS03;

    public String getThumbnailPicS02() {
        return thumbnailPicS02;
    }

    public void setThumbnailPicS02(String thumbnailPicS02) {
        this.thumbnailPicS02 = thumbnailPicS02;
    }

    public String getThumbnailPicS03() {
        return thumbnailPicS03;
    }

    public void setThumbnailPicS03(String thumbnailPicS03) {
        this.thumbnailPicS03 = thumbnailPicS03;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailPicS() {
        return thumbnailPicS;
    }

    public void setThumbnailPicS(String thumbnailPicS) {
        this.thumbnailPicS = thumbnailPicS;
    }
}
