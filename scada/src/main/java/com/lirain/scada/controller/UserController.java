package com.lirain.scada.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.lirain.scada.mina.DataLibraryProcess;
import com.lirain.scada.pojo.Userinfo;
import com.lirain.scada.service.UserinfoService;


@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserinfoService userInfo;
	
	public static Logger logger = Logger.getLogger(DataLibraryProcess.class);
	
	@RequestMapping("/showuser")
	public String toIndex(HttpServletRequest request,Model model){
//		int userId = Integer.parseInt(request.getParameter("id"));
//		User user = this.userService.getUserById(userId);
//		model.addAttribute("user", user);
		 Userinfo info =userInfo.getUserById(1);
		logger.info(JSON.toJSONString(info));
		return "showUser";
	}
	
	@RequestMapping("/logo")
	public String toIndex1(HttpServletRequest request,Model model){
//		int userId = Integer.parseInt(request.getParameter("id"));
//		User user = this.userService.getUserById(userId);
//		model.addAttribute("user", user);
	//	 Userinfo info =userInfo.getUserById(3);
	//	logger.info(JSON.toJSONString(info));
		logger.info("hello test");
		return "index";
	}
	
	
	
}
