package com.youu.youu.restapi.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.util.CommonUtils;
import com.atguigu.util.MD5Utils;
import com.youu.youu.manager.bean.TUser;
import com.youu.youu.manager.dao.TUserMapper;
import com.youu.youu.restapi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	TUserMapper tUserMapper;
	
	public static void main(String[] args) {
        String hashAl = "MD5";
        Object crObject = "123456";
        Object saltObject = ByteSource.Util.bytes("youu");
        Object resultObject = new SimpleHash(hashAl, crObject, saltObject, 6);
        System.out.println(resultObject);
    }
	
	
	@Override
	public boolean register(TUser tUser) {
		//获取用户信息 密码加密存储
		String hashAl = "MD5";
		Object crObject = tUser.getPassword();
		Object saltObject = ByteSource.Util.bytes(tUser.getLoginacct());
		Object resultObject = new SimpleHash(hashAl, crObject,saltObject,6);
		String password = resultObject.toString();
		System.out.println(password);
		tUser.setPassword(password);;
		tUser.setCreateDate(CommonUtils.simpleFormatDate(new Date()));
		int i = 0;
		try {
			 i = tUserMapper.insert(tUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i==1;
	}
	@Override
	public boolean login(TUser tUser) {
		List<TUser> list = tUserMapper.select(tUser);
		return false;
	}
	@Override
	public List<TUser> getAll() {
		
		return tUserMapper.selectByExample(null);
	}
	@Override
	public TUser getManagerByLoginacc(String username) {
		TUser tUser = new TUser();
		tUser.setLoginacct(username);
		List<TUser> list = tUserMapper.select(tUser);
		return list.size() == 0 ? null : list.get(0);
		
	}
}
