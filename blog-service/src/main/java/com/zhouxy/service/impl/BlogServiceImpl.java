package com.zhouxy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhouxy.dao.IBlogDao;
import com.zhouxy.entity.Blog;
import com.zhouxy.service.IBlogService;

@Service("blogService")
public class BlogServiceImpl implements IBlogService {

	@Autowired
	private IBlogDao blogDao;

	@Override
	public List<Blog> findBlogListByUsername(String username) {
		return blogDao.findBlogListByUsername(username);
	}

}
