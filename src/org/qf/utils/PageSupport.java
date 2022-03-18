package org.qf.utils;

public class PageSupport {
    private int currentPageNo = 1;//当前页面
    private int totalCount = 0;//总记录数
    private int pageSize = 0;//页面容量
    private int totalPageCount=1;//总页面数

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        if(currentPageNo>0)
        this.currentPageNo = currentPageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if(totalCount>0)
        this.totalCount = totalCount;
        setTotalPageCountByRs();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize>0)
        this.pageSize = pageSize;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public void setTotalPageCountByRs(){
        if(this.totalCount%pageSize==0) this.totalPageCount=this.totalCount/this.pageSize;
        else if(this.totalCount%pageSize>0) this.totalPageCount=this.totalCount/this.pageSize+1;
        else this.totalPageCount=0;
    }
}
