package com.youu.youu.restapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youu.youu.manager.bean.TUser;
import com.youu.youu.restapi.bean.ScwReturn;
import com.youu.youu.restapi.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping("/login")
	public ScwReturn<TUser> login(TUser tUser) {
		System.out.println("进入登录方法");
		String loginacct = tUser.getLoginacct();
		String password = tUser.getPassword();
		if (StringUtils.isEmpty(password) && StringUtils.isEmpty(loginacct)) {
			// 输入不对
			return ScwReturn.fail("用户名和密码不能为空", null, null);
		}
		//获取当前登录对象
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginacct,password);
		
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			System.out.println("用户名或密码错误");
			return ScwReturn.fail("用户名或密码错误", tUser, null);
		}
		System.out.println("成功");
		return ScwReturn.success("成功", tUser, null);
	}

	/**
	 * 检测用户名是否存在 检查用户名密码是否正确 用户是否在线 在线 禁止登陆（后期做踢出在线用户） 未登录状态 允许登陆 修改登录状态 记录登录时间
	 */

	@RequestMapping("/regist")
	public ScwReturn<TUser> regist(TUser tUser) {
		System.out.println("进入注册方法");
		//List<TUser> list = userService.getAll();
		Map<String, Object> hashMap = new HashMap<>();
		
		boolean flag = false;
		try {
			flag = userService.register(tUser);
		} catch (Exception e) {
			System.out.println("注册失败");
		}
		if (flag) {
			return ScwReturn.success("注册成功", tUser, null);
		} else {
			return ScwReturn.fail("注册失败", null, hashMap);
		}
	}

	/**
	 * 1.来到注册界面，通过失去焦点事件检测用户名邮箱是否合法唯一 2.保存用户信息 loginacct username password email
	 */
}
