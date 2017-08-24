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

	public PageBean findCountPage(String cid,int currentCount,int currentPage) {
		PageBean<Product> pageBean = new PageBean<Product>();
		int totalCount = dao.getCount(cid);
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		//select * from product where cid=? limit ?,?;
		int index = (currentPage-1)*currentCount;
		List<Product> list = dao.finProductByPage(cid,index,currentCount);
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		return pageBean;
	}

	public Product findProductById(String pid) {
		ProductDao dao = new ProductDao();
		Product product = dao.findProductById(pid);
		return product;
	}
}

