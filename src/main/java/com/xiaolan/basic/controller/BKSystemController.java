package com.xiaolan.basic.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 后台系统控制器
 *
 * @author dsir
 * @create 2017-09-20 10:40
 **/
@Controller
@RequestMapping
public class BKSystemController {
    private Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        log.info("====================登录页面====================");
        return "login";
    }
}
