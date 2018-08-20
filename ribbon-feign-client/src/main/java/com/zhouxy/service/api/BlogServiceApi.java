package com.zhouxy.service.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhouxy.entity.Blog;

/**
 * 使用Ribbon + RestTemplate + Hystrix 调用服务
 */
@Service
public class BlogServiceApi {
	private static final Logger logger = LoggerFactory.getLogger(BlogServiceApi.class);

	@Autowired
    RestTemplate restTemplate;

	/**
	 * 使用HystrixCommand注解，在fallbackMethod属性中指定fallback的方法
	 * @param username
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "findBlogListByUsernameError")
    public List<Blog> findBlogListByUsername(String username) {
		logger.info("------------calling trace findBlogListByUsername  ");
		Blog[] blogs = this.restTemplate.getForObject("http://BLOG-SERVICE/blogController/findBlogListByUsername?userName="+username,Blog[].class);
		List<Blog> list = JSONObject.parseArray(JSON.toJSONString(blogs), Blog.class);
		return list;
    }
	
	/**
	 * 覆写fallbackMethod中指定的方法，注意，此方法的返回值，参数必须与原方法一致
	 * @param username
	 * @return
	 */
	public List<Blog> findBlogListByUsernameError(String username) {
        System.out.println("sorry,调用findBlogListByUsername方法过程出错!");
        return null;
    }

}
