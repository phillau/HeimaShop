package com.itheima.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.service.UserService;

/**
 * Servlet implementation class ActiveServlet
 */
@WebServlet("/active")
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ActiveServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String activeCode = request.getParameter("activeCode");
		UserService service = new UserService();
		service.active(activeCode);
		
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
