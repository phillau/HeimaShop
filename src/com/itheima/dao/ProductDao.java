package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
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

	public int getCount(String cid) {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT COUNT(*) FROM product WHERE cid=?";
		try {
			long l = (Long) runner.query(sql, new ScalarHandler(),cid);
			return (int)l;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<Product> finProductByPage(String cid, int index, int currentCount) {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE cid=? LIMIT ?,?;";
		try {
			return (List<Product>) runner.query(sql, new BeanListHandler<Product>(Product.class),cid,index,currentCount);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Product findProductById(String pid) {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE pid=?;";
		try {
			return (Product) runner.query(sql, new BeanHandler<Product>(Product.class),pid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
