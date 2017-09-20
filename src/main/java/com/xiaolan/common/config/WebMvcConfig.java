package com.xiaolan.common.config;

import com.xiaolan.common.properties.ConstantProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    ConstantProperties constantProperties;

    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/error").setStatusCode(HttpStatus.NOT_FOUND).setViewName("error/404");
        registry.addViewController("/error").setStatusCode(HttpStatus.FORBIDDEN).setViewName("error/404");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        registry.addResourceHandler("/uploadpic/**").addResourceLocations("file:///" + constantProperties.getUploadPath() + ConstantProperties.UPLOAD_PIC_PATH);
        super.addResourceHandlers(registry);
    }
}
