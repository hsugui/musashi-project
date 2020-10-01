package com.musashicoin.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Order1 {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String type;
	private BigDecimal value;
	
	@Enumerated(EnumType.STRING)
	private Currency currency;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
}
