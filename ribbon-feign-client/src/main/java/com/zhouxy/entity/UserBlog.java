package com.zhouxy.entity;

import java.util.List;

public class UserBlog {
	
	private List<Blog> blogs;
	private User user;
	
	public List<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
