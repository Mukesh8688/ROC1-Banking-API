package com.bankapplication.model.account;

import java.util.Date;

public class AccountTransaction {
	
	
	private int transactionId;
	private int transactionType;
	private double amount;
	private int accountNumber;
	private double totalBalance;
	private Date transactionDate;
	
	
	// Constructor 
	public AccountTransaction() {
		
	}
	
	public AccountTransaction(int transactionId, int transactionType, double amount, int accountNumber,
			double totalBalance, Date transactionDate) {
		
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.accountNumber = accountNumber;
		this.totalBalance = totalBalance;
		this.transactionDate = transactionDate;
	}
   
	//Setting and Getting
	
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "[ Transaction Id=" + transactionId + ", Transaction Type=" + transactionType
				+ ", Amount=$" + amount + ", Account Number=" + accountNumber + ", Total Balance=$" + totalBalance
				+ ", Transaction Date=" + transactionDate + " ]";
	}
	
	
	
	

}
