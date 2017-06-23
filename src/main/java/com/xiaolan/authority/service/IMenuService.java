package com.xiaolan.authority.service;

import com.xiaolan.authority.domain.Menu;

import java.util.List;


public interface IMenuService {
    /**
     * 添加权限
     *
     * @param menu
     * @return
     * @throws Exception
     */
    Menu addMenu(Menu menu);

    /**
     * 添加权限
     *
     * @param menu
     * @return
     * @throws Exception
     */
    void delMenu(Menu menu);

    /**
     * 更新权限
     *
     * @param menu
     * @return
     * @throws Exception
     */
    Menu upMenu(Menu menu);

    /**
     * 查询一个具体权限
     *
     * @param menu
     * @return
     * @throws Exception
     */
    Menu findMenu(Menu menu);

    /**
     * 查询所有权限
     *
     * @return
     * @throws Exception
     */
    List<Menu> listOfMenu(Menu menu);

}
