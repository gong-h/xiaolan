package com.xiaolan.authority.service.impl;

import com.xiaolan.authority.domain.Menu;
import com.xiaolan.authority.repository.IMenuRepository;
import com.xiaolan.authority.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private IMenuRepository menuRepository;

    @Override
    public Menu addMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public void delMenu(Menu menu) {
        menuRepository.delete(menu);
    }

    @Override
    public Menu upMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu findMenu(Menu menu) {
        return menuRepository.findOne(menu.getId());
    }

    @Override
    public List<Menu> listOfMenu(Menu menu) {
        if (menu == null)
            menu = new Menu();
        ExampleMatcher matcher = ExampleMatcher.matching()//
                .withMatcher("name", GenericPropertyMatchers.contains())//
                .withMatcher("pname", GenericPropertyMatchers.contains())//
                ;
        return menuRepository.findAll(Example.of(menu, matcher));
    }

}
