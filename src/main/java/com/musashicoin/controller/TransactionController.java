package com.musashicoin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.musashicoin.dto.NewTransactionRequest;
import com.musashicoin.model.NewTransaction;
import com.musashicoin.repository.TransactionRepository;

@Controller
@RequestMapping("transaction")
public class TransactionController {
	
	@Autowired
	private TransactionRepository transactionRepository;

	@GetMapping("transfer")
	public String transfer(NewTransactionRequest request) {
		return "transaction/transfer";
	}
	
	@GetMapping("buy")
	public String buy(NewTransactionRequest request) {
		return "transaction/buy";
	}
	
	@PostMapping("last")
	public String last(@Valid NewTransactionRequest request, BindingResult result) {
		if(result.hasErrors()) return "transaction/transfer";
		
		NewTransaction newTransaction = request.toNewTransaction();
		transactionRepository.save(newTransaction);
		
		return "redirect:/home";
	}
	

}
