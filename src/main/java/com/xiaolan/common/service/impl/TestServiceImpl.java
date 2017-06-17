package com.xiaolan.common.service.impl;/**
 * Created by dsir on 2017/6/5.
 */

import com.xiaolan.common.service.ITestService;
import org.springframework.stereotype.Repository;

/**
 * 测试类
 *
 * @author dsir
 * @create 2017-06-05 14:14
 **/
@Repository
public class TestServiceImpl  implements ITestService{
    public void showInfo() {
        System.out.println("============================");
    }
}
