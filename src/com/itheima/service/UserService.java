package com.itheima.service;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;

public class UserService {
	public boolean regist(User user){
		UserDao dao = new UserDao();
		int row = dao.regist(user);
		return row>0?true:false;
	}

	public void active(String activeCode) {
		UserDao dao = new UserDao();
		dao.active(activeCode);
	}

	public boolean checkUsername(String username) {
		UserDao dao = new UserDao();
		Long isExist = dao.checkUsername(username);
		return isExist>0?true:false;
	}
}
