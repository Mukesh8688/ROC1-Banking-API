package com.bankapplication.model.customer;

import org.apache.log4j.Logger;

import com.bankapplication.dao.BankServicesDAOInterface;
import com.bankapplication.dao.impl.BankServicesDAOImpl;
import com.bankapplication.exception.BusinessException;
import com.bankapplication.model.LogInModel;

import  java.util.Scanner;

public class CustomerViewAccBalance {
	
	//Log4j
	
	private static Logger logger = Logger.getLogger(CustomerViewAccBalance.class);
	
	
	Scanner scanner = new Scanner(System.in);
	
	// DAO object 
	
	BankServicesDAOInterface BankDAOObject = new BankServicesDAOImpl();
	
	private int accountNumber;
	private int byCustomerId;
	
	public static int choice ;
	
	
	//Constructor
	
	public CustomerViewAccBalance() {
		
	}

	
	public CustomerViewAccBalance(int accountNumber, int byCustomerId) {
		this.accountNumber = accountNumber;
		this.byCustomerId = byCustomerId;
	}
    
	
	
	// Getter and Setter


	public int getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}


	public int getByCustomerId() {
		return byCustomerId;
	}


	public void setByCustomerId(int byCustomerId) {
		this.byCustomerId = byCustomerId;
	}


	public void askAccoutParameter() throws BusinessException{
		
		System.out.println();
		logger.fatal("Select Type to view account");
		logger.fatal("------------------------------");
		logger.fatal("1) By Account Number");
		logger.fatal("2) View All Accounts");
		logger.fatal("------------------------------");
		
	    choice = Integer.parseInt(scanner.nextLine());
		
		if(choice == 1) {
			logger.fatal("Enter Account Number:");
			setAccountNumber(Integer.parseInt(scanner.nextLine()));
			logger.trace("Entered ACC:" + getAccountNumber());
		}else if(choice == 2) {
		     int byUserid =   BankDAOObject.getUserId(LogInModel.getUsername());
		     int byCustomerId = BankDAOObject.getCustomerId(byUserid);
		     setByCustomerId(byCustomerId);
		}else {
			throw new BusinessException("Please Choose option to view Account Balance ...");
		}
		
		
	}
	
	public int askAccoutParametersByEmployee() throws BusinessException{
		
		int choiceByEmployee = 0;
		
		System.out.println();
		logger.fatal("Select Type to view account");
		logger.fatal("------------------------------");
		logger.fatal("1) By Account Number");
		logger.fatal("2) View All Accounts");
		logger.fatal("------------------------------");
		
	    choice = Integer.parseInt(scanner.nextLine());
		
		if(choice == 1) {
			logger.fatal("Enter Account Number:");
			setAccountNumber(Integer.parseInt(scanner.nextLine()));
			choiceByEmployee = 1;
			logger.trace("Entered ACC:" + getAccountNumber());
			logger.trace("Entered choiceEmployee:" + choiceByEmployee);
		}else if(choice == 2) {
			choiceByEmployee = 2;
			logger.trace("Entered choiceEmployee:" + choiceByEmployee);
			
		}else {
			throw new BusinessException("Please Choose option to view Account Balance ...");
		}
		
		
		return choiceByEmployee ;
		
		
	}
	


}
