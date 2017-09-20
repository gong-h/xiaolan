package com.xiaolan.common.intreceptor;

import com.xiaolan.authority.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录后的拦截器，用户配置一些相同数据
 */
public class UserLoadIntreceptor implements HandlerInterceptor {
    Log log = LogFactory.getLog(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("====================================UerLoadIntreceptor.preHandle===================================" + request.getRequestURL() + "====" + response.getStatus());
        //设置用户访问菜单
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {//用户
            User userDetails = (User) authentication.getPrincipal();
            //request.setAttribute("user", userDetails);
            //request.setAttribute("menuTree", userDetails.getMenuTree());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("====================================UerLoadIntreceptor.postHandle===================================" + request.getRequestURL() + "====" + response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("====================================UerLoadIntreceptor.afterCompletion===================================" + request.getRequestURL() + "====" + response.getStatus());
    }
}
