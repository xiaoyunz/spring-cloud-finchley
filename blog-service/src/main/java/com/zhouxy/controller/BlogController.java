package com.zhouxy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhouxy.entity.Blog;
import com.zhouxy.service.IBlogService;

import io.swagger.annotations.ApiOperation;

@RestController      //相当于@ResponseBody＋ @Controller
@RequestMapping("/blogController")
@RefreshScope    //启用消息总线必须添加此注解
public class BlogController {
	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	
	@Value("${service-info.service-name}")
	private String serviceName;
	
	@Value("${service-info.service-ip}")
	private String serviceIp;
	
	@Value("${service-info.service-port}")
	private String servicePort;
	
	@Value("${service-info.version}")
	private String version;

	@Autowired
	private IBlogService blogService;

	@ApiOperation(value = "info接口", notes = "应用服务信息")
	@GetMapping("/info")
	public String info(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("========info============");
		return "应用服务信息， service：" + serviceName + "   ip:" + serviceIp + "   port:" + servicePort + "   version:" + version;
	}
	
	@ApiOperation(value = "查询博客列表", notes = "查询博客列表（根据用户名查询博客类别）")
	@GetMapping("/findBlogListByUsername")
    public List<Blog> findByUsername(@RequestParam(value="userName",required = true) String userName){
        return blogService.findBlogListByUsername(userName);
    }
	
	

}
