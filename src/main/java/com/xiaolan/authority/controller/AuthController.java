package com.xiaolan.authority.controller;

import com.xiaolan.authority.domain.Menu;
import com.xiaolan.authority.domain.Role;
import com.xiaolan.authority.domain.User;
import com.xiaolan.authority.service.IMenuService;
import com.xiaolan.authority.service.IRoleService;
import com.xiaolan.authority.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final Log logger = LogFactory.getLog(getClass());
    @Autowired
    IUserService userService;
    @Autowired
    IRoleService roleService;
    @Autowired
    IMenuService menuService;

    @RequestMapping("/user")
    public String user() {
        User user = new User();
        user.setUsername("测试用户");
        user.setNickname("test-user");
        logger.info("================用户==================:" + user);

        user = userService.addUser(user);
        logger.info("================添加用户==============:" + user);

        user.setNickname("up-user");
        user = userService.upUser(user);
        logger.info("================修改用户==============:" + user);

        user = userService.findUser(user);
        logger.info("================查询用户==============:" + user);

        List<User> listOfUser = userService.listOfUser(null);
        logger.info("================所有列表用户==============:" + listOfUser);

        User user1 = new User();
        user1.setUsername("测试用户");
        List<User> partListOfUser = userService.listOfUser(user1);
        logger.info("================部分列表用户==============:" + partListOfUser);

        userService.delUser(user);
        logger.info("================删除用户==============:" + user);
        logger.info("================用户==================");

        return "success";
    }

    @RequestMapping("/rolemenu")
    public String rileMenu() {
        logger.info("================角色==================:");
        Role role = new Role();
        // role.setId(7);
        role.setName("新增角色");
        role.setSn("add-role");
        role.setDescription("新增角色描述");
        logger.info(role);
        logger.info(role.getMenus());

        logger.info("================所有列表菜单==============");
        List<Menu> listOfMenu = menuService.listOfMenu(null);

        logger.info("================添加角色菜单==============");
        role.setMenus(listOfMenu);
        Role dbrole = roleService.addRole(role);
        logger.info(dbrole);
        logger.info(dbrole.getMenus());

        return "success";
    }

    @RequestMapping("/rolemm")
    public String roleMenuManyToMany() {
        List<Role> listOfRole = roleService.listOfRole(null);
        logger.info("================所有列表角色==============:" + listOfRole);
        logger.info("================所有列表角色==============:" + listOfRole.get(1).getMenus());

        return "success";
    }

    @RequestMapping("/role")
    public String role() {
        Role role = new Role();
        role.setName("测试角色");
        role.setSn("test-role");
        role.setDescription("角色描述");
        logger.info("================角色==================:" + role);

        role = roleService.addRole(role);
        logger.info("================添加角色==============:" + role);

        role.setName("up-role");
        role = roleService.upRole(role);
        logger.info("================修改角色==============:" + role);

        role = roleService.findRole(role);
        logger.info("================查询角色==============:" + role);

        List<Role> listOfRole = roleService.listOfRole(null);
        logger.info("================所有列表角色==============:" + listOfRole);

        Role role1 = new Role();
        // role1.setName("角色2");
        role1.setDescription("2");
        List<Role> partListOfRole = roleService.listOfRole(role1);
        logger.info("================部分列表角色==============:" + partListOfRole);

        roleService.delRole(role);
        logger.info("================删除角色==============:" + role);
        logger.info("================角色==================");

        return "success";
    }

    @RequestMapping("/menu")
    public String menu() {
        Menu menu = new Menu();
        menu.setName("测试菜单");
        menu.setSn("test-menu");
        menu.setPname("1213");
        logger.info("================菜单==================:" + menu);

        menu = menuService.addMenu(menu);
        logger.info("================添加菜单==============:" + menu);

        menu.setName("up-menu");
        menu = menuService.upMenu(menu);
        logger.info("================修改菜单==============:" + menu);

        menu = menuService.findMenu(menu);
        logger.info("================查询菜单==============:" + menu);

        List<Menu> listOfMenu = menuService.listOfMenu(null);
        logger.info("================所有列表菜单==============:" + listOfMenu);

        Menu menu1 = new Menu();
        // menu1.setName("菜单2");
        menu1.setPname("2");
        List<Menu> partListOfMenu = menuService.listOfMenu(menu1);
        logger.info("================部分列表菜单==============:" + partListOfMenu);

        menuService.delMenu(menu);
        logger.info("================删除菜单==============:" + menu);
        logger.info("================菜单==================");

        return "success";
    }
}
