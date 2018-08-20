package com.zhouxy.service;

import java.util.List;

import com.zhouxy.entity.Blog;

public interface IBlogService {

	public List<Blog> findBlogListByUsername(String username);


}
