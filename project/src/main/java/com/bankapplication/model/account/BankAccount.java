package com.bankapplication.model.account;

import java.util.Date;

public class BankAccount {
	
	private int accountNumber;
	private double balance ;
	private double openingBalance ;
	private String accountName;
	private int customerId;
	private Date dateOpened;
    private double interest;
    private int accountStatus;
    private int accountType;
    private String branchLoc;
    
    
    //Constructor
    public BankAccount() {
		

	}
    
	public BankAccount(double balance, double openingBalance, String accountName, int customerId, Date dateOpened,
			double interest, int accountStatus, int accountType, String branchLoc) {
		
		this.balance = balance;
		this.openingBalance = openingBalance;
		this.accountName = accountName;
		this.customerId = customerId;
		this.dateOpened = dateOpened;
		this.interest = interest;
		this.accountStatus = accountStatus;
		this.accountType = accountType;
		this.branchLoc = branchLoc;
	}
	
	
	
	// Getter and Setter

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Date getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public int getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public String getBranchLoc() {
		return branchLoc;
	}

	public void setBranchLoc(String branchLoc) {
		this.branchLoc = branchLoc;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	

	
	
    
    
    
    

}
