package com.xiaolan.common.dao;

import java.util.List;

/**
 * @author dsir
 * @create 2017-06-05 13:39
 */
public interface IBaseDao {
    /**
     * 保存数据
     *
     * @param o
     * @return 保存失败 返回0
     */
    Integer save(Object o) throws Exception;

    /**
     * 删除数据
     *
     * @param o
     * @return 删除失败 返回0
     */
    Integer delete(Object o) throws Exception;

    /**
     * 更新数据
     *
     * @param o
     * @return 更新失败 返回0
     */
    Integer update(Object o) throws Exception;

    /**
     * 根据id获取一个对象
     *
     * @param o
     * @return
     */
    Object findOne(Object o) throws Exception;

    /**
     * 获取所有数据
     *
     * @return
     */
    List list() throws Exception;
}
