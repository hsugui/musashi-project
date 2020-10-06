package com.musashicoin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.musashicoin.model.NewTransaction;
import com.musashicoin.repository.TransactionRepository;

@Controller
public class HomeController {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<NewTransaction> newTransactions = transactionRepository.findAll();
		model.addAttribute("newTransactions", newTransactions);
		return "home";
	}
	
	public String onError() {
		return "redirect:/home";
	}
	
}
