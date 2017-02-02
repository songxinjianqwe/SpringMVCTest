package me.newsong.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import me.newsong.domain.User;
import me.newsong.enums.UsernameExistedException;
import me.newsong.service.UserService;
import me.newsong.utils.BaseServlet;
import me.newsong.utils.CommonUtils;

public class UserServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2507976860667993658L;

	public String register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		UserService service = ctx.getBean(UserService.class);
		String verifyCode = (String) request.getSession().getAttribute("verifyCode");
		if (!user.getVerifyCode().trim().toUpperCase().equals(verifyCode)) {
			request.setAttribute("status", "验证码错误");
			return "Forward:/register.jsp";
		} else {
			try {
				service.addUser(user);
			} catch (UsernameExistedException e) {
				request.setAttribute("status", "用户名重复");
				return "Forward:/register.jsp";
			}
		}
		return "Redirect:/index.jsp";
	}

	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		UserService service = ctx.getBean(UserService.class);
		User realUser = service.findByUsername(user.getUsername());
		if (realUser == null || !user.getPassword().equals(realUser.getPassword())) {
			request.setAttribute("status", "用户名或密码错误");
			return "Forward:/views/login.jsp";
		} else {
			request.getSession().setAttribute("username", user.getUsername());
			Cookie cookie = new Cookie("username", user.getUsername());
			response.addCookie(cookie);
			cookie.setMaxAge(60 * 60);
			return "Redirect:/index.jsp";
		}
	}
	
	public String activate(HttpServletRequest request, HttpServletResponse response){
		return null;
	}
	
	public String modifyPassword(HttpServletRequest request, HttpServletResponse response){
		return null;
	}
	
	public String exit(HttpServletRequest request, HttpServletResponse response){
		return null;
	}
}
