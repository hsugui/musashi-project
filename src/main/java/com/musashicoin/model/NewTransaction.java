package com.musashicoin.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NewTransaction {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String publicKey;
	private float value;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
}
