package com.common.lottery;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class LotteryHistoryData  implements Serializable {
    @JSONField(name = "page")
    private String page;//	当前页数
    @JSONField(name = "pageSize")
    private String pageSize;//	每页返回条数
    @JSONField(name = "totalPage")
    private String totalPage;//总页数
    @JSONField(name = "lotteryResList")
    private List<LotteryResList> lotteryResList;//历史详情

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public List<LotteryResList> getLotteryResList() {
        return lotteryResList;
    }

    public void setLotteryResList(List<LotteryResList> lotteryResList) {
        this.lotteryResList = lotteryResList;
    }
}
