package com.zhouxy.service;

import com.zhouxy.entity.User;

public interface IUserService {

	public User findByUsername(String username);

	public boolean createUser(User user);

	public boolean modifyUser(User user);

	public boolean deleteUser(int id);

}
