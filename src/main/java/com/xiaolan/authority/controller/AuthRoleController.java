package com.xiaolan.authority.controller;

import com.xiaolan.authority.domain.Menu;
import com.xiaolan.authority.domain.Role;
import com.xiaolan.authority.service.IMenuService;
import com.xiaolan.authority.service.IRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/role")
public class AuthRoleController {
    private Log log = LogFactory.getLog(getClass());
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;

    /**
     * 用户列表
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {
        log.debug("AuthRoleController.list");
        model.addAttribute("roleList", roleService.listOfRole(null));
        return "/auth/role-list";
    }

    /**
     * 进入新增页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model) {
        log.debug("AuthRoleController.edit");
        model.addAttribute("type", "0");//新增操作标识
        model.addAttribute("role", new Role());
        model.addAttribute("action", "/role/save");
        model.addAttribute("errMsg", null);
        return "/auth/role-edit";
    }

    /**
     * 进入编辑页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        log.debug("AuthRoleController.edit (ID:" + id + ")");
        log.debug(roleService.findById(id));
        model.addAttribute("type", "1");//更新操作标识
        model.addAttribute("role", roleService.findById(id));
        model.addAttribute("action", "/role/save");
        model.addAttribute("errMsg", null);
        return "/auth/role-edit";
    }

    /**
     * 保存与更新  区别在于是否拥有id
     *
     * @param model
     * @param role
     * @return
     */
    @RequestMapping("/save")
    public String save(Model model, Role role) {
        log.debug("AuthRoleController.save ( Role = " + role + ")");
        String errMsg = null;
        String type = null;
        try {
            if (role.getId() != null) {//修改
                type = "1";
                errMsg = "更新失败!";
                Role temp = roleService.findById(role.getId());
                temp.setDescription(role.getDescription());
                roleService.upRole(temp);
            } else {
                type = "0";
                errMsg = "添加失败!";
                role.setName("");
                role = roleService.addRole(role);
                role.setName("ROLE_" + role.getId());
                roleService.upRole(role);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("type", type);
            model.addAttribute("action", "/role/save");
            model.addAttribute("user", role);
            model.addAttribute("errMsg", errMsg);
            return "/auth/role-edit";
        }
        return "redirect:/role/list";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/del/{id}")
    public String del(@PathVariable Integer id) {
        log.debug("AuthRoleController.del （id = " + id + ")");
        Role byId = roleService.findById(id);
        if (byId != null) {
            roleService.delRole(byId);
        }
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/grant/{id}")
    public String grantMenus(Model model, @PathVariable Integer id, String errMsg) {
        log.debug("AuthRoleController.grantMenus (id=" + id + ",errMsg=" + errMsg + ")");
        Role role = roleService.findById(id);
        List<Menu> menuList = menuService.listOfSort();
        for (Iterator<Menu> iter = menuList.iterator(); iter.hasNext(); ) {
            Menu menu = (Menu) iter.next();
            if (CollectionUtils.isEmpty(role.getMenus())) {
                break;
            }
            if (role.getMenus().contains(menu)) {
                menu.setChecked(true);
            }
        }
        System.out.println(menuList);
        model.addAttribute("role", role);
        model.addAttribute("menuList", menuList);
        model.addAttribute("errMsg", errMsg);
        return "/auth/role-grant-menus";
    }

    @RequestMapping(value = "/grant")
    public String grant(Model model, Integer id, String menus) {
        log.debug("AuthRoleController.grant (id=" + id + ",menus=" + menus + ")");
        Role role = roleService.findById(id);
        try {
            if (role == null) {
                throw new Exception("角色信息错误：" + id);
            }
            if (StringUtils.isEmpty(menus)) {
                throw new Exception("至少请选择一项");
            }
            List<Menu> menuList = new ArrayList<Menu>();
            for (String mid : menus.split("[,]")) {
                menuList.add(menuService.findById(Integer.valueOf(mid)));
            }
            role.setMenus(menuList);
            roleService.upRole(role);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "redirect:/role/grant/" + id + "?errMsg=" + e.getMessage();
        }
        return "redirect:/role/list";
    }
}
