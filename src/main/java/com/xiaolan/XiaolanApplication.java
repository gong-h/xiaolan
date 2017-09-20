package com.xiaolan;

import com.xiaolan.authority.domain.User;
import com.xiaolan.authority.service.IUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class XiaolanApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(XiaolanApplication.class, args);
        //initPassword(ctx);
        //addUsers(ctx);
    }

    private static void addUsers(ApplicationContext ctx) {
        IUserService suserService = (IUserService) ctx.getBean("userServiceImpl");
        for (int i = 0; i < 100; i++) {
            String name = "name" + i + "";
            User user = new User(name, name, "111111", 0, false, "111111" + i);
            suserService.addUser(user);
        }
    }

    public static void initPassword(ApplicationContext ctx) {
        IUserService suserService = (IUserService) ctx.getBean("userServiceImpl");
        User su = suserService.findByUsername("admin");
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);// 将密码加密
        System.out.println("修改前密码:" + su.getPassword());//
        su.setPassword(bc.encode(su.getPassword()));// 可以先设置初始密码：000000
        System.out.println("修改后密码:" + su.getPassword());//
        // 然后使用密码为key值进行加密，运行主类后，会自动加密密码，可连接数据库查看。
        suserService.upUser(su);// 运行一次后记得注释这段重复加密会无法匹配

    }
}
