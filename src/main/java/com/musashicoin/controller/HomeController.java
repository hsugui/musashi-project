package com.musashicoin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.musashicoin.model.Order1;
import com.musashicoin.repository.OrderRepository;

@Controller
public class HomeController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/home")
	public String home(Model model) {

		List<Order1> orders = orderRepository.findAll();
		model.addAttribute("orders", orders);
		return "home";
	}
	
	public String onError() {
		return "redirect:/home";
	}
	
}
