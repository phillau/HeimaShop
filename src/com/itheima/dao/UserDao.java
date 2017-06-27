package com.itheima.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.itheima.domain.User;
import com.itheima.utils.DataSourceUtils;

public class UserDao {
	public int regist(User user){
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		int update = 0;
		try {
			update = runner.update(sql, user.getUid(),user.getUsername(),user.getPassword(),
					user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),
					user.getSex(),user.getState(),user.getCode());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;
	}
}
