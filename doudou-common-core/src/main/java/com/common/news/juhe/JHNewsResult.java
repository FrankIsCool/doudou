package com.common.news.juhe;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class JHNewsResult {
    @JSONField(name = "stat")
    private String stat;
    @JSONField(name = "data")
    private List<JHNewsData> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<JHNewsData> getData() {
        return data;
    }

    public void setData(List<JHNewsData> data) {
        this.data = data;
    }
}
