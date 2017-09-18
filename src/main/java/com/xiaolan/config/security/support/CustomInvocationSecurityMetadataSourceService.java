package com.xiaolan.config.security.support;

import com.xiaolan.authority.domain.Menu;
import com.xiaolan.authority.domain.Role;
import com.xiaolan.authority.service.IRoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    private Log log = LogFactory.getLog(this.getClass());

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    @Autowired
    private IRoleService roleService;

    /**
     * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。
     * PostConstruct在构造函数之后执行,init()方法之前执行。
     */
    @PostConstruct
    public void loadResourceDefine() {
        // 在Web服务器启动时，提取系统中的所有权限。
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        /*
         * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
		 */
        List<Role> list = roleService.listOfRole(null);
        ConfigAttribute ca = null;
        Collection<ConfigAttribute> atts = null;
        for (Role role : list) {
            ca = new SecurityConfig(role.getName());
            for (Menu menu : role.getMenus()) {
                String url = menu.getHref();
                if(!StringUtils.isEmpty(url)){
                    if (resourceMap.containsKey(url)) {
                        atts = resourceMap.get(url);
                    } else {
                        atts = new ArrayList<ConfigAttribute>();
                    }
                    atts.add(ca);
                    resourceMap.put(url, atts);
                }
            }
        }
    }

    /**
     * 根据URL，找到相关的权限配置。
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // object 是一个URL，被用户请求的url。</span>
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if (resourceMap == null) {
            loadResourceDefine();
        }
        Iterator<String> ite = resourceMap.keySet().iterator();
      log.debug(filterInvocation.getHttpRequest());
        while (ite.hasNext()) {
            String resURL = ite.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
