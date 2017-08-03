package com.itheima.service;

import java.util.List;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Category;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;

public class ProductService {
	ProductDao dao = new ProductDao();

	public List<Product> findHotProductList() {
		List<Product> productList = dao.findHotProductList();
		return productList;
	}

	public List<Product> findNewProductList() {
		List<Product> productList = dao.findNewProductList();
		return productList;
	}

	public List<Category> findCategoryProductList() {
		List<Category> categoryList = dao.findCategoryProductList();
		return categoryList;
	}

	public void findCountPage() {
		PageBean pageBean = new PageBean();
		int currentPage = 1;
		int currentCount = 12;
		int totalCount = dao.getCount();
		int totalPage = (int) Math.ceil(totalCount/currentCount);
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
	}
}

