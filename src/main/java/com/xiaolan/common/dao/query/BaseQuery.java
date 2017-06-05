package com.xiaolan.common.dao.query;

import java.util.ArrayList;
import java.util.List;

/**
 * ORACLE分页查询的基类
 *
 * @author dsir
 * @create 2017-06-05 13:39
 */
public abstract class BaseQuery {
    private List<String> constions = new ArrayList<String>();
    private Integer currentPage = 1;                        // 当前页
    private Integer pageSize = 10000;                    // 每行多少条数据

    /**
     * 添加查询条件 表的字段+数据
     *
     * @param query
     */
    public void addQuery(String query) {
        constions.add(query);
    }

    /**
     * 提供一个接口传递出querySql属性
     *
     * @return
     */
    public String getQuerySql() {
        constions.clear();
        StringBuilder sql = new StringBuilder("");

        defineQuery();

        for (int i = 0; i < constions.size(); i++) {
            if (i == 0) {
                sql.append(" WHERE ");
            } else {
                sql.append(" AND ");
            }
            sql.append("(" + constions.get(i) + ")");
        }
        // System.out.println("sql=" + sql.toString());
        return sql.toString();
    }

    /**
     * 存放自定义查询条件
     */
    public abstract void defineQuery();

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        if (currentPage != null && !currentPage.trim().equals(""))
            setCurrentPage(Integer.valueOf(currentPage));
    }

    public void setCurrentPage(Integer currentPage) {
        if (currentPage < 1) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize < 1) {
            pageSize = 1;
        }

        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 输出一个分页查询 下标 ORACLE
     *
     * @return
     */
    public Integer getBeginIndexOracle() {
        return (currentPage - 1) * pageSize + 1;
    }

    /**
     * 输出一个分页查询 下标 ORACLE
     *
     * @return
     */
    public Integer getEndIndex() {
        return currentPage * pageSize;
    }

    /**
     * 输出一个分页查询 下标 MYSQL
     *
     * @return
     */
    public Integer getBeginIndex() {
        return (currentPage - 1) * pageSize;
    }
}
