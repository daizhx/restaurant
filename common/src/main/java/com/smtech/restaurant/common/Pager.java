package com.smtech.restaurant.common;

import java.util.List;

public class Pager<T> {

    private List<T> data;
    private int pageSize;
    private int totalPages;

    private int curPageStartIndex;
    private int curPageEndIndex;
    private int curPageIndex;

    public Pager(List<T> data, int pageSize) {
        this.data = data;
        this.pageSize = pageSize;
        curPageStartIndex = 0;
        curPageEndIndex = pageSize < data.size() ? pageSize - 1 : data.size() - 1;
        totalPages = data.size()/pageSize + (data.size() % pageSize == 0 ? 0 : 1);
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurPageStartIndex() {
        return curPageStartIndex;
    }

    public int getCurPageEndIndex() {
        return curPageEndIndex;
    }

    // 返回下一页的起始数据Index
    public int nextPage(){
        if(curPageIndex == (totalPages - 1)){
            //最后一页
            return curPageStartIndex;
        }
        curPageIndex++;
        curPageStartIndex += pageSize;
        curPageEndIndex = curPageEndIndex + pageSize;

        if(curPageEndIndex > data.size() - 1){
            curPageEndIndex = data.size() - 1;
        }

        return curPageStartIndex;
    }

    public int prePage(){
        if(curPageIndex == 0){
            //第一页
            return curPageStartIndex;
        }

        curPageIndex--;
        curPageStartIndex -= pageSize;
        curPageEndIndex = curPageStartIndex + (pageSize - 1);

        return curPageStartIndex;
    }

    public List<T> getCurPageData(){
        return data.subList(curPageStartIndex,curPageEndIndex+1);
    }

    @Override
    public String toString() {
        if(totalPages > 0 ){
            return "第 " + (curPageIndex+1) + "/" + totalPages + " 页";
        }
        return "第 0/0 页";
    }
}
