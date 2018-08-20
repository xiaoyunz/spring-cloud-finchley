package com.zhouxy.dao;

import java.util.List;

import com.zhouxy.entity.Blog;

public interface IBlogDao {
	
	public List<Blog> findBlogListByUsername(String username);
	

}
