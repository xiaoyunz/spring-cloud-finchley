package com.zhouxy.service.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhouxy.entity.User;

/**
 * 使用Feign + Hystrix 调用服务
 */
@Service
@FeignClient(name = "user-service",fallbackFactory = UserServiceApiHystrixFallbackFactory.class)
public interface UserServiceApi {
	
	@GetMapping("/userController/findUserByUsername")
    public User findUserByUsername(@RequestParam(value="userName",required = true) String userName);
	
    @PostMapping("/userController/createUser")
    public boolean createUser(@RequestBody User user);
	
    @PutMapping("/userController/modifyUser")
    public boolean modifyUser(@RequestBody User user);
	
    @DeleteMapping("/userController/deleteUser/{id}")
    public boolean deleteUser(@PathVariable(name = "id") Integer id);

}
