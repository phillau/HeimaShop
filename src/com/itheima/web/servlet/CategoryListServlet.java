package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.itheima.domain.Category;
import com.itheima.service.ProductService;
import com.itheima.utils.JedisPoolUtils;

import redis.clients.jedis.Jedis;

@WebServlet("/categoryList")
public class CategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CategoryListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		Jedis jedis = JedisPoolUtils.getJedis();
		String categoryListJson = jedis.get("categoryListJson");
		if(categoryListJson==null){
			System.out.println("缓存没有数据 查询数据库");
			List<Category> categoryList = service.findCategoryProductList();
			Gson gson = new Gson();
			categoryListJson = gson.toJson(categoryList);
			jedis.set("categoryListJson", categoryListJson);
			System.out.println("categoryListJson="+categoryListJson);
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(categoryListJson);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
