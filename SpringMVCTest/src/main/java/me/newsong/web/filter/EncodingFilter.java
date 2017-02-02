package me.newsong.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * @author Sinjin Song
 * 处理全站编码问题
 */

public class EncodingFilter implements Filter {
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		filterChain.doFilter(request, response);
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
