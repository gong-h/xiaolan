package com.xiaolan.common.dao;

import com.xiaolan.common.bean.BeaconORM;
import com.xiaolan.common.dao.query.BaseQuery;

import java.util.List;


/**
 * Beacon信息维护数据库连接
 *
 * @author dsir
 * @create 2017-06-05 13:39
 */
public interface IBeaconDao extends IBaseDao {
    /**
     * 高级条件查询
     *
     * @param bq
     * @return
     * @throws Exception
     */
    List<BeaconORM> query(BaseQuery bq) throws Exception;

    /**
     * 分页查询 + 高级条件查询
     *
     * @param bq
     * @return
     * @throws Exception
     */
    List<BeaconORM> queryPage(BaseQuery bq) throws Exception;

    /**
     * 分页查询 + 高级条件查询 总条数
     *
     * @param bq
     * @return
     * @throws Exception
     */
    Integer queryPageCount(BaseQuery bq) throws Exception;
}
