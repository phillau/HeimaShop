package com.itheima.web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;

@WebServlet("/productInfo")
public class ProductInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String cid = request.getParameter("cid");
		String currentPage = request.getParameter("currentPage");
		ProductService service = new ProductService();
		Product product = service.findProductById(pid);
		request.setAttribute("product", product);
		
		Cookie[] cookies = request.getCookies();
		String pids = pid;
		if(cookies!=null){
			for(Cookie cookie:cookies){
//				System.out.println(cookie.getName());
				if("pids".equals(cookie.getName())){
					 String pidString = cookie.getValue();
					 LinkedList<String> pidList = new LinkedList<String>(Arrays.asList(pidString.split("-")));
					 if(pidList.contains(pid)){
						 pidList.remove(pid);
					 }
					 pidList.addFirst(pid);
					 StringBuffer sb = new StringBuffer();
					 for(int i=0;i<pidList.size();i++){
						 sb.append(pidList.get(i));
						 sb.append("-");
					}
					 pids = sb.substring(0, sb.length()-1);
				} 
			}
		}
			 System.out.println("pids="+pids); 
			 Cookie cookie = new Cookie("pids",pids);
			 response.addCookie(cookie);
		
		request.setAttribute("cid", cid);
		request.setAttribute("currentPage", currentPage);
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
