package com.pcms.core.util;

import java.util.List;

public final class ResultPage {

    public final static int DEFAULT_PAGE_SIZE = 10;

    public final static int DEFAULT_PAGE_NO = 1;

    private int currentPage = 1;

    private int pageSize = 10;
    
    private int pageCount = 1;

    private long totalCount;

    private boolean haveNextPage;

    private boolean havePrePage;
    
    private List items;

    public ResultPage(long totalCount, int pageSize, int currentPage,
            List items) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.pageCount = operatorPageCount();
        this.items = items;
    }

    public int operatorPageCount() {
        int count = 0;
        if (pageSize != 0) {
            count = (int) (totalCount / pageSize);
            if (totalCount % pageSize != 0) {
                count++;
            }
        }

        return count;
    }

    public int getCurrentPage() {
        currentPage = currentPage < pageCount ? currentPage
                : pageCount;
        currentPage = currentPage < 1 ? 1 : currentPage;

        return currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public boolean isHaveNextPage() {
        haveNextPage = false;
        if ((pageCount > 1) && (pageCount > getCurrentPage())) {
            haveNextPage = true;
        }
        return haveNextPage;
    }

    public boolean isHavePrePage() {
        havePrePage = false;
        if ((pageCount > 1) && (currentPage > 1)) {
            havePrePage = true;
        }
        return havePrePage;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
