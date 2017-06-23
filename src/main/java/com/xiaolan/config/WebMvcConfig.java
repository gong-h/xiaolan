package com.xiaolan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/hello1").setViewName("hello1");
		registry.addViewController("/hello2").setViewName("hello2");
		registry.addViewController("/hello3").setViewName("hello3");
	}
}
