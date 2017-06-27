package com.itheima.service;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;

public class UserService {
	public boolean regist(User user){
		UserDao dao = new UserDao();
		int row = dao.regist(user);
		return row>0?true:false;
	}
}
