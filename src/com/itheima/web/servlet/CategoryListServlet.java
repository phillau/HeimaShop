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

@WebServlet("/categoryList")
public class CategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CategoryListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		List<Category> categoryList = service.findCategoryProductList();
		response.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		String json = gson.toJson(categoryList);
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
