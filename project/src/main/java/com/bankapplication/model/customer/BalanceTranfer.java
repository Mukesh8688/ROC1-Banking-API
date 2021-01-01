package com.bankapplication.model.customer;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankapplication.dao.BankServicesDAOInterface;
import com.bankapplication.dao.impl.BankServicesDAOImpl;
import com.bankapplication.exception.BusinessException;
import com.bankapplication.model.account.BankAccountRegister;

public class BalanceTranfer {
	
	private double sendAmount;
	private int sendingAccountNumber;
	private int receivingAccountNumber;
	

	//log4j
	private static Logger logger = Logger.getLogger(BankAccountRegister.class);
	
	
	//Scanner
	private static Scanner scanner = new Scanner(System.in);
	
	// Create DAO  Object 
	
	private static BankServicesDAOInterface  bankServiceDAO = new BankServicesDAOImpl();
	
	
	public BalanceTranfer() {
		
	}
	
	
	public BalanceTranfer(double sendAmount, int sendingAccountNumber, int receivingAccountNumber) {
		
		this.sendAmount = sendAmount;
		this.sendingAccountNumber = sendingAccountNumber;
		this.receivingAccountNumber = receivingAccountNumber;
	}
    
	
	
	//Setter and Getter

	public double getSendAmount() {
		return sendAmount;
	}


	public void setSendAmount(double sendAmount) {
		this.sendAmount = sendAmount;
	}


	public int getSendingAccountNumber() {
		return sendingAccountNumber;
	}


	public void setSendingAccountNumber(int sendingAccountNumber) {
		this.sendingAccountNumber = sendingAccountNumber;
	}


	public int getReceivingAccountNumber() {
		return receivingAccountNumber;
	}


	public void setReceivingAccountNumber(int receivingAccountNumber) {
		this.receivingAccountNumber = receivingAccountNumber;
	}
	
	
	
	public static  BalanceTranfer getAllRequiredInfo() throws BusinessException {
		
		BalanceTranfer balancetransfer = new BalanceTranfer();
		
		
		  System.out.println("Balance Transfer");
		  System.out.println("-------------------");
		  logger.fatal("How much would you like to transfer ? : $");
		  balancetransfer.setSendAmount(Double.parseDouble(scanner.nextLine()));
		  logger.fatal("Please enter Account Number from where you want to transfer: ");
		  balancetransfer.setSendingAccountNumber(Integer.parseInt(scanner.nextLine()));
		  
		  logger.fatal("Please enter Acoount Number where you want to transfer: ");
		  balancetransfer.setReceivingAccountNumber(Integer.parseInt(scanner.nextLine()));
		  
          
		
		  return balancetransfer;
		
	}
	
	
	public static int transferAmount(BalanceTranfer balancetransfer) throws BusinessException{
		int success =0;
		
		BankAccountRegister bankAccountDetails = new BankAccountRegister() ;
		
		if(balancetransfer.getSendingAccountNumber() == balancetransfer.getReceivingAccountNumber()){
			
			throw new BusinessException("Sender Account Number and Receiving Account Number shouldn't be same ...\nPlease try again...");
		}
		
		try {
			
			bankAccountDetails = bankServiceDAO.transferAmount(balancetransfer.getSendingAccountNumber(), 
					balancetransfer.getReceivingAccountNumber(), balancetransfer.getSendAmount());
			
			logger.fatal("Now available "+ bankAccountDetails.getAccountName() +" Balance :$ " + bankServiceDAO.getAccountBalance(bankAccountDetails.getCustomerAccountNum()));
			
			
			success = 1;
			
		} catch (BusinessException e) {
			logger.fatal(e.getMessage());
		}
		
		
		
		return success;
	}



}
