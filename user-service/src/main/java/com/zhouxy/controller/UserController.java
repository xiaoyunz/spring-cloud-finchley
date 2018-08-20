package com.zhouxy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhouxy.entity.User;
import com.zhouxy.service.IUserService;
import com.zhouxy.util.BPwdEncoderUtils;

import io.swagger.annotations.ApiOperation;

@RestController      //相当于@ResponseBody＋ @Controller
@RequestMapping("/userController")
@RefreshScope    //消息中心必须添加此注解
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Value("${service-info.service-name}")
	private String serviceName;
	
	@Value("${service-info.service-ip}")
	private String serviceIp;
	
	@Value("${service-info.service-port}")
	private String servicePort;
	
	@Value("${service-info.version}")
	private String version;

	@Autowired
	private IUserService userService;

	@ApiOperation(value = "info接口", notes = "应用服务信息")
	@GetMapping("/info")
	public String info(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("========info============");
		return "应用服务信息， service：" + serviceName + "   ip:" + serviceIp + "   port:" + servicePort + "   version:" + version;
	}
	
	@ApiOperation(value = "查询用户", notes = "查询用户（根据用户名查询）")
	@GetMapping("/findUserByUsername")
    public User findUserByUsername(@RequestParam(value="userName",required = true) String userName){
		System.out.println("==========findUserByUsername=============="+userName);
        return userService.findByUsername(userName);
    }
	
	/**
	 * postman工具测试时，须将《@RequestBody User user》 实体参数封装成json格式，选择raw（json）提交
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "创建用户", notes = "创建用户")
    @PostMapping("/createUser")
    public boolean createUser(@RequestBody User user){
        //参数判读省略,判读该用户在数据库是否已经存在省略
        String entryPassword= BPwdEncoderUtils.BCryptPassword(user.getPassword());
        user.setPassword(entryPassword);
        return userService.createUser(user);
    }
	
	@ApiOperation(value = "更新用户", notes = "更新用户")
    @PutMapping("/modifyUser")
    public boolean modifyUser(@RequestBody User user){
		return userService.modifyUser(user);
    }
	
	@ApiOperation(value = "删除用户", notes = "删除用户")
    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUser(@PathVariable(name = "id") Integer id){
		System.out.println("========= id : " + id);
		return userService.deleteUser(id);
    }
	

}
