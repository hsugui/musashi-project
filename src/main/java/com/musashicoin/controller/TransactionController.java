package com.musashicoin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.musashicoin.dto.NewTransactionRequest;
import com.musashicoin.model.Order1;
import com.musashicoin.repository.OrderRepository;

@Controller
@RequestMapping("transaction")
public class TransactionController {
	
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("transfer")
	public String transfer(NewTransactionRequest request) {
		return "transaction/transfer";
	}
	
	@PostMapping("new")
	public String newTransaction(@Valid NewTransactionRequest request, BindingResult result) {
		if(result.hasErrors()) return "transaction/transfer";
		
		//Transaction transaction = request.toTransaction();
		Order1 order1 = request.toOrder();
		orderRepository.save(order1);
		
		return "redirect:/home";
	}
	
	@GetMapping("buy")
	public String buy(NewTransactionRequest request) {
		return "transaction/buy";
	}
}
