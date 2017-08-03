package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.domain.Category;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.utils.DataSourceUtils;

public class ProductDao {

	public List<Product> findHotProductList() {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE is_hot=? limit ?,?";
		try {
			return runner.query(sql, new BeanListHandler<Product>(Product.class),1,0,9);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Product> findNewProductList() {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM product ORDER BY pdate DESC limit ?,?";
		try {
			return runner.query(sql, new BeanListHandler<Product>(Product.class),0,9);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Category> findCategoryProductList() {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM category";
		try {
			return runner.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getCount() {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT COUNT(*) FROM product";
		try {
			return (Integer) runner.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
