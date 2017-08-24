package com.itheima.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.PageBean;
import com.itheima.service.ProductService;

@WebServlet("/productListByCid")
public class productListByCidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		String cid = (String) request.getParameter("cid");
//		System.err.println("cid="+cid);
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int currentCount = 12;
		PageBean pageBean = service.findCountPage(cid,currentCount,currentPage);
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
		for(Cookie cookie:cookies){
			
		}
		}
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
