package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.CommonUtils;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, parameterMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		user.setUid(CommonUtils.getUUID());
		user.setTelephone(null);
		user.setState(0);
		user.setCode(CommonUtils.getUUID());
		
		//将user传递给service
		UserService service = new UserService();
		boolean isRegistSuccess = service.regist(user);
	
		//是否注册成功
		if(isRegistSuccess){
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
