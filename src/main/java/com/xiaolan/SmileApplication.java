package com.xiaolan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 注意
public class SmileApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmileApplication.class, args);
		// SpringApplication app = new
		// SpringApplication(SmileApplication.class);
		// ApplicationContext ctx = app.run(args);
		// IUserService suserService = (IUserService)
		// ctx.getBean("userServiceImpl");
		// User su = suserService.findByUsername("admin");
		// BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);// 将密码加密
		// su.setPassword(bc.encode(su.getPassword()));// 可以先设置初始密码：000000
		// System.out.println("密码" + su.getPassword());//
		// 然后使用密码为key值进行加密，运行主类后，会自动加密密码，可连接数据库查看。
		// suserService.upUser(su);// 运行一次后记得注释这段重复加密会无法匹配
	}
}
