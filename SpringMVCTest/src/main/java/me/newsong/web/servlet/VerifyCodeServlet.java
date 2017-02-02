package me.newsong.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.newsong.utils.VerifyCode;

public class VerifyCodeServlet extends HttpServlet {
	/**
	 * 获取验证码和提交表单不是同一个请求，而是两个请求
	 * 所以要讲验证码保存到session中
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VerifyCode code = new VerifyCode();
		code.createRandomCode(response.getOutputStream());
		request.getSession().setAttribute("verifyCode", code.getCode());
	}

}
