package com.musashicoin.dto;

import javax.validation.constraints.NotBlank;

import com.musashicoin.model.NewTransaction;

public class NewTransactionRequest {
	
	@NotBlank
	private String publicKey;
	@NotBlank
	private String transferValue;
	
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

	public NewTransaction toNewTransaction() {
		NewTransaction newTransaction = new NewTransaction();
		newTransaction.setPublicKey(publicKey);
		newTransaction.setValue(Float.parseFloat(transferValue));
		return newTransaction;
	}
}
