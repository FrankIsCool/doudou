package com.common.jsonResult;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;

public class Page extends RowBounds implements Serializable {
    //一共多少条数据
    private int row;
    //当前第几页
    private int pageNow;
    //每页多少条数据
    private int pageSize;
    //一共多少页
    private int num;

    public final static int PAGE_ZERO = 0;
    public final static int PAGE_ONE = 1;

    public final static int PAGE_SIZE_10 = 10;
    public final static int PAGE_SIZE_20 = 20;
    public final static int PAGE_SIZE_50 = 50;
    public final static int PAGE_SIZE_100 = 100;
    public final static int PAGE_SIZE_200 = 200;
    public Page() {
        this(PAGE_ZERO,PAGE_ONE,PAGE_SIZE_20,PAGE_ZERO);
    }

    public Page(int nowNum, int size) {
        this(PAGE_ZERO,nowNum<1? PAGE_ONE:nowNum,size<1? PAGE_SIZE_20:nowNum,PAGE_ZERO);
    }

    public Page(int row, int pageNow, int pageSize, int num) {
        this.row = row;
        this.pageSize = pageSize;
        this.pageNow = pageNow;
        this.num = num;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.num = row>0&&(row%pageSize)>0?(row%pageSize)+1:row%pageSize;
        this.row = row;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
