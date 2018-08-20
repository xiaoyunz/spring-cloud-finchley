package com.zhouxy.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhouxy.entity.Blog;
import com.zhouxy.entity.User;
import com.zhouxy.entity.UserBlog;
import com.zhouxy.service.IUserBlogService;
import com.zhouxy.service.api.BlogServiceApi;
import com.zhouxy.service.api.UserServiceApi;

@Service("userBlogService")
public class UserBlogServiceImpl implements IUserBlogService {
	
	@Autowired
	private UserServiceApi userServiceApi;
	
	@Autowired
	private BlogServiceApi blogServiceApi;

	/**
	 * 调用用户服务接口和博客服务接口
	 * userServiceApi使用Feign + Hystrix 调用服务
	 * blogServiceApi使用Ribbon + RestTemplate + Hystrix 调用服务
	 */
	@Override
	public UserBlog queryUserBlogInfo(String userName) {
		UserBlog userBlog = new UserBlog();
		User user = userServiceApi.findUserByUsername(userName);
		List<Blog> blogs = blogServiceApi.findBlogListByUsername(userName);
		userBlog.setUser(user);
		userBlog.setBlogs(blogs);
		return userBlog;
	}

}
