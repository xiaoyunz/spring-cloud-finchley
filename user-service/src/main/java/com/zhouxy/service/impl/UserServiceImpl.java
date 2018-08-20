package com.zhouxy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhouxy.dao.IUserDao;
import com.zhouxy.entity.User;
import com.zhouxy.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public boolean createUser(User user) {
		try {
			userDao.createUser(user);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean modifyUser(User user) {
		try {
			userDao.modifyUser(user);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(int id) {
		try {
			userDao.deleteUser(id);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
