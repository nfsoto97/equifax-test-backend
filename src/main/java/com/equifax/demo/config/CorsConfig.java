package com.equifax.demo.config;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Configuration
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsConfig implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException, java.io.IOException {
		// TODO Auto-generated method stub
		HttpServletResponse resp=(HttpServletResponse) response;
		HttpServletRequest req=(HttpServletRequest) request;
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "DELETE, GET, OPTIONS, PATCH, POST, PUT");
		resp.setHeader("Access-Control-Max-Age", "3600");
		resp.setHeader("Access-Control-Allow-Headers","x-request-with,authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");
		if("OPTIONS".equalsIgnoreCase(req.getMethod())) {
			resp.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			chain.doFilter(req, resp);
		}
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	

}
