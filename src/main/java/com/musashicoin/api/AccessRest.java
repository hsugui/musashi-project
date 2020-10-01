package com.musashicoin.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musashicoin.interceptor.AccessInterceptor;
import com.musashicoin.interceptor.AccessInterceptor.Access;

@RequestMapping("accesses")
@RestController
public class AccessRest {

	@GetMapping
	public List<Access> getAccesses() {
		return AccessInterceptor.accesses;
	}
	
}
