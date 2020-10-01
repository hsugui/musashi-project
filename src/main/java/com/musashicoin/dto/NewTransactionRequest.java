package com.musashicoin.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.musashicoin.model.Order1;

public class NewTransactionRequest {
	
	@NotBlank
	private String customerName;
	@NotBlank
	private String publicKey;
	@NotBlank
	private String transferValue;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getTransferValue() {
		return transferValue;
	}
	public void setTransferValue(String transferValue) {
		this.transferValue = transferValue;
	}
	public Order1 toOrder() {
		Order1 order1 = new Order1();
		order1.setType("DOLLAR"); //fazer enum
		order1.setValue(new BigDecimal(13435));
		return order1;
	}
}
