package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.CommonUtils;
import com.itheima.utils.MailUtils;

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
		
		Map<String, String[]> properties = request.getParameterMap();
		User user = new User();
		try {
			ConvertUtils.register(new Converter(){
				@Override
				public Object convert(Class arg0, Object arg1) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date date = null;
					try {
						date = format.parse(arg1.toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return date;
				}
				
			}, Date.class);
			BeanUtils.populate(user, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		user.setUid(CommonUtils.getUUID());
		user.setTelephone(null);
		user.setState(0);
		String activeCode = CommonUtils.getUUID();
		user.setCode(activeCode);
		
		//将user传递给service
		UserService service = new UserService();
		boolean isRegistSuccess = service.regist(user);
	
		//是否注册成功
		if(isRegistSuccess){
			String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户"
					+ "<a href='http://localhost:8080/HeimaShop/active?activeCode="+activeCode+"'>"
							+ "http://localhost:8080/HeimaShop/active?activeCode="+activeCode+"</a>";
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
		}else{
			response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
