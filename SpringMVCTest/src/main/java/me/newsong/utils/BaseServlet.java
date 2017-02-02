package me.newsong.utils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName == null || methodName.isEmpty()){
			System.out.println("未输入方法名");
			throw new RuntimeException("Do not input methodName");
		}
		
		Method method = null;
		try {
			method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("未找到"+methodName+"方法");
			throw new RuntimeException("can not find "+methodName);
		}
		String result = null;
		try {
			result = (String) method.invoke(this, request,response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println(methodName+ "方法内部抛出异常");
			throw new RuntimeException(e);
		}
		if(result != null){
			String [] strs = result.split(":");
			String type = strs[0],path = strs[1];
			if(type.equals("Forward")){
				request.getRequestDispatcher(path).forward(request, response);
			}else if(type.equals("Redirect")){
				response.sendRedirect(request.getContextPath()+path);
			}else{
				throw new RuntimeException(type+" has not been supported");
			}
		}
	}
}
