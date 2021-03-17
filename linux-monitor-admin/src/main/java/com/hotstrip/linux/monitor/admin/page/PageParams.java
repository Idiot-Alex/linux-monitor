package com.hotstrip.linux.monitor.admin.page;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageParams implements Serializable {
    private int pageNo;
    private int pageSize;
    private int offset;
    private int totalCount;

    public PageParams() {
        this.pageNo = 1;
        this.pageSize = 10;
        this.offset = (this.pageNo - 1) * this.pageSize;
    }

    public PageParams(int pageNo, int pageSize, int totalCount) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.offset = (this.pageNo - 1) * this.pageSize;
        this.totalCount = totalCount;
    }

    public PageParams(final Integer pageNo, final Integer pageSize) {
        this.pageNo = pageNo == null || pageNo <= 0 ? 0 : pageNo;
        this.pageSize = pageSize == null || pageSize <= 0 ? 10 : pageSize;
        this.offset = (this.pageNo - 1) * this.pageSize;
    }
}
