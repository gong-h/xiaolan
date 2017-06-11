package com.xiaolan.common.dao.query;

import java.util.List;

/**
 * 分页查询 已知 (当前页 currentPage和 分页行数 pageSize) 总条数dataTotal 分页数据pages 每页多少条pageSize 当前页码currentPage
 * <p>
 * <p>
 * 获取上一页 lastPage 获取下一页 nextPage 总页数 pageTotal 开始下标 startIndex
 *
 * @author dsir
 * @create 2017-06-05 13:39
 */
public class ResultPage<T> {
    private List<T> rows = null; // 存放分页数据
    private int total;                // 数据总条数

    private int pageSize = 10;    // 每页条数
    private int currentPage = 1;    // 当前页码

    public ResultPage(int dataTotal, List<T> rows, int currentPage, int pageSize) {
        this.total = dataTotal;
        this.rows = (List<T>) rows;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public void setPages(List<T> rows) {
        this.rows = rows;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getRows() {
        return rows;
    }

    /**
     * 获取数据总条数
     *
     * @return
     */
    public int getTotal() {
        return total;
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public int getPageTotal() {
        return total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
    }

    /**
     * 上一页页码
     *
     * @return
     */
    public int getLastPage() {
        return currentPage > 1 ? currentPage - 1 : currentPage;
    }

    /**
     * 下一页页码
     *
     * @return
     */
    public int getNextPage() {
        return currentPage < getPageTotal() ? currentPage + 1 : currentPage;
    }

    /**
     * 每页条数
     *
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 获取当前页码
     *
     * @return
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * MYSQL 开始下标
     *
     * @return
     */
    public int getStartIndex() {
        return (currentPage - 1) * pageSize;
    }

    /**
     * ORACLE 开始下标
     *
     * @return
     */
    public int getStartIndexOracle() {
        return (currentPage - 1) * pageSize + 1;
    }

    /**
     * ORACLE 结束下标
     *
     * @return
     */
    public int getEndIndex() {
        return currentPage * pageSize;
    }

}
