package com.zhouxy.dao;

import com.zhouxy.entity.User;

public interface IUserDao {
	
	public User findByUsername(String username);
	
	public void createUser(User user);

	public void modifyUser(User user);
	
	public void deleteUser(int id);

}
