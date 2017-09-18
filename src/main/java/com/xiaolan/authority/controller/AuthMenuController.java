package com.xiaolan.authority.controller;

import com.xiaolan.authority.domain.Menu;
import com.xiaolan.authority.service.IMenuService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class AuthMenuController {
    private Log log = LogFactory.getLog(getClass());
    @Autowired
    private IMenuService menuService;

    @RequestMapping("/list")
    public String execute(Model model) {
        log.debug("AuthMenuController.execute");
        List<Menu> menuList = menuService.listOfSort();
        model.addAttribute("menuList", menuList);
        return "/auth/menu-list";
    }

    @RequestMapping("/tree")
    public String menu(Model model) {
        log.debug("AuthMenuController.menu");
        List<Menu> menuList = menuService.listOfMenu(null);
        model.addAttribute("menuTree", menuService.parseMenus(menuList));
        return "/auth/menu-list";
    }
}
