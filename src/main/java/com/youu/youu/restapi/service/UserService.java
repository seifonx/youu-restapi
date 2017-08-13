package com.youu.youu.restapi.service;


import java.util.List;

import com.youu.youu.manager.bean.TUser;

public interface UserService {

	

	public boolean register(TUser tUser);

	public boolean login(TUser tUser);

	List<TUser> getAll();

	public TUser getManagerByLoginacc(String username);

}
