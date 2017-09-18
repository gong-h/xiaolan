package com.xiaolan.config.security.support;

import com.xiaolan.authority.domain.Menu;
import com.xiaolan.authority.domain.Role;
import com.xiaolan.authority.domain.User;
import com.xiaolan.authority.service.IMenuService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录成功后操作函数
 */

@Configuration
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Log log = LogFactory.getLog(getClass());
    private String url = null;//成功跳转地址
    @Autowired
    private IMenuService menuService;

    public LoginSuccessHandler() {
    }

    public LoginSuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        loadInfo(request, authentication);
        if (!StringUtils.isEmpty(url)) {
            setAlwaysUseDefaultTargetUrl(true);
            setDefaultTargetUrl(request.getContextPath() + url);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private void loadInfo(HttpServletRequest request, Authentication authentication) {
        // 获得授权后可得到用户信息 可使用SUserService进行数据库操作
        User userDetails = (User) authentication.getPrincipal();
        /* Set<SysRole> roles = userDetails.getSysRoles(); */
        // 输出登录提示信息
        System.out.println("管理员 " + userDetails.getUsername() + " 登录");
        System.out.println("角色：" + userDetails.getRoles());
        System.out.println("IP :" + getIpAddress(request));
        System.out.println(authentication.getAuthorities());
        List<Menu> menuList = new ArrayList<Menu>();
        for (Role role : userDetails.getRoles()) {
            for (Menu menu : role.getMenus()) {
                if (!menuList.contains(menu) && menu.getDisplay() == new Menu().getDisplay()) {
                    menuList.add(menu);
                }
            }
        }
        //userDetails.setMenuTree(menuService.parseMenus(menuList));
    }

    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}