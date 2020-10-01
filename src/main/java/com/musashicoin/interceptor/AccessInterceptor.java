package com.musashicoin.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AccessInterceptor extends HandlerInterceptorAdapter {

	public static List<Access> accesses = new ArrayList<>();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Access access = new Access();
		access.path = request.getRequestURI();
		access.date = LocalDateTime.now();
		
		request.setAttribute("access", access);
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Access access = (Access) request.getAttribute("access");
		access.duration = Duration.between(access.date, LocalDateTime.now());
		accesses.add(access);
	}
	
	public static class Access {
		private String path;
		private LocalDateTime date;
		private Duration duration;
		
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public LocalDateTime getDate() {
			return date;
		}
		public void setDate(LocalDateTime date) {
			this.date = date;
		}
		public Duration getDuration() {
			return duration;
		}
		public void setDuration(Duration duration) {
			this.duration = duration;
		}
	}
	
}
