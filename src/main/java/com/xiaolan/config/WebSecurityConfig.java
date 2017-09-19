package com.xiaolan.config;

import com.xiaolan.authority.service.IUserService;
import com.xiaolan.config.security.support.CustomFilerSecurityInterceptor;
import com.xiaolan.config.security.support.LoginSuccessHandler;
import com.xiaolan.config.security.support.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthenticationProvider provider;//自定义验证
    @Autowired
    private IUserService userService;
    @Autowired
    private CustomFilerSecurityInterceptor mySecurityFilter;
    @Autowired
    private WebSecurityConfigSettings mySecuritySettings;

    // http://localhost:8080/login 输入正确的用户名密码 并且选中remember-me则登陆成功，转到 index页面
    // 再次访问index页面无需登录直接访问
    // 访问http://localhost:8080/home不拦截，直接访问，
    // 访问http://localhost:8080/hello需要登录验证后，且具备“ADMIN”权限hasAuthority("ADMIN")才可以访问
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class)// 在正确的位置添加我们自定义的过滤器
                .authorizeRequests()//
                .antMatchers(mySecuritySettings.getPermitUrlArray()).permitAll()// 访问：/home无需登录认证权限
                .anyRequest().authenticated()// 任何请求拦截
                .and()// ====
                .formLogin()// 1.定制登录操作
                .loginPage(mySecuritySettings.getLoginPage())// 2.定制登录页面的访问地址
                .successHandler(loginSuccessHandler())// 登录成功后可使用loginSuccessHandler()存储用户信息，可选
                .defaultSuccessUrl(mySecuritySettings.getDefaultSuccessUrl(), true)// 3.指定登录成功后转向的页面
                // .defaultSuccessUrl(UrlConstant.DEFAULT_SUCCESS_URL)//3.指定登录成功后转向的页面
                .failureUrl(mySecuritySettings.getFailureUrl())// 4.指定登录失败后转向的页面
                .permitAll()//
                .and()// ====
                .rememberMe()// 5.开启cookie存储用户信息
                .tokenValiditySeconds(mySecuritySettings.getIntTokenValiditySeconds())// 6.指定cookie有效期
                .key("mykey")// 7.cookie中的私钥
                .and()// ====
                .logout()// 8.定制注销行为
                // .logoutUrl(mySecuritySettings.getLogoutUrl())// 9.指定注销的URL路径
                .logoutSuccessUrl(mySecuritySettings.getLogoutSuccessUrl())// 10.注销成功后转向的页眉
                .permitAll() //
                .invalidateHttpSession(true)//
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
        // 不删除凭据，以便记住用户
        auth.eraseCredentials(false);
        auth.authenticationProvider(provider);
    }

    // 设置不拦截规则
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(mySecuritySettings.getIgnoringMatchersArray());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

}