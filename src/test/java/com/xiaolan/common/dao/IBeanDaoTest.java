package com.xiaolan.common.dao;

import com.xiaolan.common.bean.BeaconORM;
import com.xiaolan.common.dao.query.BaseQuery;
import com.xiaolan.common.dao.query.ResultPage;
import com.xiaolan.common.service.ITestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试类
 *
 * @author dsir
 * @create 2017-06-05 13:39
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class IBeanDaoTest {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    IBeaconDao beaconBiz;
    @Autowired
    ITestService service;
    BeaconORM orm = new BeaconORM();

    @Test
    public void testInsert() throws Exception {
        orm.setAddr("新建一个1");
        System.out.println(beaconBiz.save(orm));
    }

    @Test
    public void testDelete() throws Exception {
        orm.setUuid(32L);
        System.out.println(beaconBiz.delete(orm));
    }

    @Test
    public void testUpdate() throws Exception {
        orm.setUuid(1L);
        orm = (BeaconORM) beaconBiz.findOne(orm);
        System.out.println(orm);
        orm.setAddr("修改信息");
        System.out.println(beaconBiz.update(orm));
    }

    @Test
    public void testFindOne() throws Exception {
        orm.setUuid(-32L);
        //orm.setUuid(32L);
        System.out.println(beaconBiz.findOne(orm));
    }

    @Test
    public void testList() throws Exception {
        System.out.println(beaconBiz.list());
    }

    @Test
    public void testQuery() throws Exception {
        BaseQuery bq = new BaseQuery() {
            public void defineQuery() {
                addQuery("b.addr like '%1%'");
            }
        };
        System.out.println(beaconBiz.query(bq));
    }

    @Test
    public void testQueryPage() throws Exception {
        BaseQuery bq = new BaseQuery() {
            public void defineQuery() {
                //addQuery("b.addr like '%1%'");
            }
        };
        bq.setPageSize(2);//设置每页条数
        bq.setCurrentPage(1);//设置页码
        ResultPage<BeaconORM> result = new ResultPage<BeaconORM>(beaconBiz.queryPageCount(bq), beaconBiz.queryPage(bq), bq.getCurrentPage(), bq.getPageSize());
        System.out.println("当前页码:" + result.getCurrentPage());
        System.out.println("数据总条数:" + result.getTotal());
        System.out.println("每页条数:" + result.getPageSize());
        System.out.println("总页数:" + result.getPageTotal());
        System.out.println("上一页页码:" + result.getLastPage());
        System.out.println("下一页页码:" + result.getNextPage());
        System.out.println("数据:" + result.getRows());

        //Json数据
        System.out.println(result);
    }
}
