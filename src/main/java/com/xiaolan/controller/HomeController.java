package com.xiaolan.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	Map<String, String> data = new HashMap<String, String>();

	@RequestMapping("/index")
	public String index(Model model) {
		data.put("title", "自定义标题");
		data.put("content", "自定义内容");
		data.put("extraInfo", "诶外信息");
		model.addAttribute("msg", data);
		// User userDetails = (User)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// System.out.println(userDetails);
		// System.out.println(userDetails.getRoles());
		// System.out.println(userDetails.getRoles().get(0).getMenus());
		// System.out.println(userDetails.getAuthorities());
		return "index";
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

}
