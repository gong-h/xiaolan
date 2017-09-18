package com.xiaolan.authority.controller;

import com.xiaolan.authority.domain.Role;
import com.xiaolan.authority.domain.User;
import com.xiaolan.authority.service.IRoleService;
import com.xiaolan.authority.service.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/user")
public class AuthUserController {
    private Log log = LogFactory.getLog(getClass());
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/list")
    public String list(Model model) {
        log.debug("AuthUserController.list");
        model.addAttribute("userList", userService.queryNotAdmin(new User()));
        return "/auth/user-list";
    }

    @RequestMapping("/edit")
    public String edit(Model model) {
        log.debug("AuthUserController.edit");
        model.addAttribute("type", "0");//新增操作标识
        model.addAttribute("user", new User());
        model.addAttribute("action", "/user/save");
        model.addAttribute("errMsg", null);
        return "/auth/user-edit";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        log.debug("AuthUserController.edit (id=" + id + ")");
        log.debug(userService.findById(id));
        model.addAttribute("type", "1");//更新操作标识
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("action", "/user/save");
        model.addAttribute("errMsg", null);
        return "/auth/user-edit";
    }

    @RequestMapping("/save")
    public String save(Model model, User user) {
        log.debug("AuthUserController.save (user=" + user + ")");
        String errMsg = null;
        String type = null;
        try {
            if (user.getId() != null) {//修改用户
                type = "1";
                errMsg = "更新失败!";
                User temp01 = userService.findByUsername(user.getUsername());
                User temp02 = userService.findById(user.getId());
                if (temp01 != null && !temp01.getUsername().equals(temp02.getUsername())) {
                    errMsg += "已经存在该账号该用户";
                    throw new Exception(errMsg);
                } else {
                    log.debug(user);
                    temp02.setUsername(user.getUsername());
                    temp02.setNickname(user.getNickname());
                    temp02.setPhone(user.getPhone());
                    if (user.isEnabled()) {
                        temp02.setStatus(1);
                    } else {
                        temp02.setStatus(0);
                    }
                    temp02.setEnabled(user.isEnabled());
                    log.debug(temp02);
                    userService.upUser(temp02);
                }
            } else {
                type = "0";
                errMsg = "添加失败!";
                User temp01 = userService.findByUsername(user.getUsername());
                if (temp01 != null) {
                    errMsg += "已经存在该账号该用户";
                    throw new Exception(errMsg);
                } else {
                    BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);// 将密码加密
                    user.setPassword(bc.encode("111111"));
                    user.setStatus(1);
                    user.setIsAdmin(1);
                    userService.addUser(user);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("type", type);
            model.addAttribute("action", "/user/save");
            model.addAttribute("user", user);
            model.addAttribute("errMsg", errMsg);
            return "/auth/user-edit";
        }
        return "redirect:/user/list";
    }

    @ResponseBody
    @RequestMapping(value = "/del/{id}")
    public String del(@PathVariable Long id) {
        log.debug("AuthUserController.del (id=" + id + ")");
        removeUser(id);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/grant/{id}")
    public String grantMenus(Model model, @PathVariable Long id, String errMsg) {
        log.debug("AuthUserController.grantMenus (id=" + id + ",errMsg=" + errMsg + ")");
        User user = userService.findById(id);
        List<Role> roleList = roleService.listOfRole(null);
        for (Iterator<Role> iter = roleList.iterator(); iter.hasNext(); ) {
            Role role = iter.next();
            if (CollectionUtils.isEmpty(user.getRoles())) {
                break;
            }
            if (user.getRoles().contains(role)) {
                role.setChecked(true);
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleList);
        model.addAttribute("errMsg", errMsg);
        return "/auth/user-grant-roles";
    }

    @RequestMapping(value = "/grant")
    public String grant(Long id, String roles) {
        log.debug("AuthUserController.grant (id=" + id + ",roles=" + roles + ")");
        User user = userService.findById(id);
        try {
            if (user == null) {
                throw new Exception("信息错误：" + id);
            }
            if (StringUtils.isEmpty(roles)) {
                throw new Exception("至少请选择一项");
            }
            List<Role> roleList = new ArrayList<Role>();
            for (String rid : roles.split("[,]")) {
                roleList.add(roleService.findById(Integer.valueOf(rid)));
            }
            user.setRoles(roleList);
            userService.upUser(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "redirect:/user/grant/" + id + "?errMsg=" + e.getMessage();
        }
        return "redirect:/user/list";
    }

    private User removeUser(Long id) {//删除用户，不可见
        User user = userService.findById(id);
        if (user != null) {
            user.setStatus(2);
            userService.upUser(user);
        }
        return user;
    }

    private User delUser(Long id) {//删除用户，，真实删除
        User user = userService.findById(id);
        if (user != null) {
            userService.delUser(user);
        }
        return user;
    }
}
