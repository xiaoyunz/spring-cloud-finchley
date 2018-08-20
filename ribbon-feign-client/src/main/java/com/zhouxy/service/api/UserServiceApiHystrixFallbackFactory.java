package com.zhouxy.service.api;

import org.springframework.stereotype.Component;

import com.zhouxy.entity.User;

import feign.hystrix.FallbackFactory;

/**
 * UserServiceApi 断路器回退处理类
 */
@Component
public class UserServiceApiHystrixFallbackFactory implements FallbackFactory<UserServiceApi> {

	@Override
	public UserServiceApi create(Throwable arg0) {
		return new UserServiceApi() {
			
			@Override
			public boolean modifyUser(User user) {
				/**
				 * 这里可以处理失败后的业务逻辑
				 */
				System.out.println("modifyUser fallback,错误原因：" + arg0.getMessage());
				return false;
			}
			
			@Override
			public User findUserByUsername(String userName) {
				System.out.println("findUserByUsername fallback,错误原因：" + arg0.getMessage());
				return null;
			}
			
			@Override
			public boolean deleteUser(Integer id) {
				System.out.println("deleteUser fallback,错误原因：" + arg0.getMessage());
				return false;
			}
			
			@Override
			public boolean createUser(User user) {
				System.out.println("createUser fallback,错误原因：" + arg0.getMessage());
				return false;
			}
		};
	}



}
