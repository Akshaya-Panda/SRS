package com.example.demo.bean;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "srs_tbl_creditcard")
public class CreditCardBean {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String CreditCrdNumber;
	@Column
	private String validFrom;
	@Column
	private String validTo;
	@Column
	private int balance;
	@Column
	private String userId;

	public String getCreditCrdNumber() {
		return CreditCrdNumber;
	}

	public void setCreditCrdNumber(String creditCrdNumber) {
		CreditCrdNumber = creditCrdNumber;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

//	public String getUserId() {
//		return UserId;
//	}
//
//	public void setUserId(String userId) {
//		System.out.println("Val of userid"+userId);
//		this.UserId = userId;
//	}
	

	@Override
	public String toString() {
		return "CreditCardBean [CreditCrdNumber=" + CreditCrdNumber + ", validForm=" + validFrom + ", validTo="
				+ validTo + ", balance=" + balance + ", UserId=" + userId + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
