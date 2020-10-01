package com.musashicoin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.musashicoin.interceptor.AccessInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AccessInterceptor()).addPathPatterns("/**");
	}
	
}
