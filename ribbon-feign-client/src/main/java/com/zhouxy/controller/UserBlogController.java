package com.zhouxy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zhouxy.entity.UserBlog;
import com.zhouxy.service.IUserBlogService;

@RestController      //相当于@ResponseBody＋ @Controller
@RequestMapping("/userBlogController")
public class UserBlogController {
	private static final Logger logger = LoggerFactory.getLogger(UserBlogController.class);
	
	@Value("${service-info.service-name}")
	private String serviceName;
	
	@Value("${service-info.service-ip}")
	private String serviceIp;
	
	@Value("${service-info.service-port}")
	private String servicePort;

	@Autowired
	private IUserBlogService userBlogService;

	@GetMapping("/info")
	public String info(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("========info============");
		return "应用服务信息， service：" + serviceName + "   ip:" + serviceIp + "   port:" + servicePort;
	}
	
	@GetMapping("/findUserBlogs")
    public UserBlog findUserBlogs(@RequestParam(value="userName",required = true) String userName){
		return userBlogService.queryUserBlogInfo(userName);
    }
	
	

}
