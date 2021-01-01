package com.bankapplication.model.account;


import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankapplication.dao.BankServicesDAOInterface;
import com.bankapplication.dao.impl.BankServicesDAOImpl;
import com.bankapplication.exception.BusinessException;
import com.bankapplication.model.LogInModel;
import com.bankapplication.model.customer.CustomerInfo;



public class BankAccountRegister {
	
	// Create DAO  Object for verify userid , customerid 
	
	private static BankServicesDAOInterface  bankServiceDAO = new BankServicesDAOImpl();
	
	/* Setting Date Format MM-dd-yyyy ( Month-days- year) */
	//private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	
	//log4j
	private static Logger logger = Logger.getLogger(BankAccountRegister.class);
	
	
	//Scanner
	public static Scanner scanner = new Scanner(System.in);
	
	
	// Bank Account variable declaration
	
	private int customerAccountNum ;
	private double balance ;
	private double openingBalance;
	private String accountName;
	private int customerId;
	private Date dateOpened;
	private double interest;
	private int accountStatus;
	private int accountType;
	private String branchLoc;

	
	// Constructor
	
	public  BankAccountRegister() {
		
	}
	
	
	

	public BankAccountRegister(int customerAccountNum, double balance, double openingBalance, String accountName,
			int customerId, Date dateOpened, double interest, int accountStatus, int accountType, String branchLoc) {
		this.customerAccountNum = customerAccountNum;
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
	







	public int getCustomerAccountNum() {
		return customerAccountNum;
	}


   
	

	public void setCustomerAccountNum(int customerAccountNum) {
		this.customerAccountNum = customerAccountNum;
	}




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

   
	
	public void askAccountType() {
		 System.out.println();
		 System.out.println("Select the Account What you want !!!");
		 System.out.println("====================================");
		 logger.fatal("Type 1 - Checking Account :");
		 logger.fatal("Type 2 - Saving Account :");
		 logger.fatal("Type 3 - EXIT ");
		 System.out.println("=====================================");
		 
		 System.out.println();
		// System.out.println("**** NOTE *** By Default it takes Checking Account****");	   
	}
	
	
	public int verifyAccountType() {
		
		int accType = 3;
	   
		 try {
		    	accType = Integer.parseInt(scanner.nextLine());
		    	logger.error("Accouttype:" + accType);
		    }catch(NumberFormatException e) {
//		        System.out.println("SORRY !! Please choose Integer Number(1-3) *** ");
//		        System.out.println();
		    }
		    	
		return accType;
	}

	
	
	
	public int getAccountTypeChoice() throws BusinessException{
		
		
		int accountTypeChoice = 0;
		
		askAccountType() ;
		
		accountTypeChoice = verifyAccountType();
	
		logger.trace("AccoutTypeChoice:" + accountTypeChoice);
				
		/* asking customer personal information */
				
		if(accountTypeChoice == 1) {
			System.out.println("Welcome to Checking Account");
			System.out.println("-----------------------------");
		
		}else if(accountTypeChoice == 2) {
			System.out.println("Welcome to Saving Account");
			System.out.println("-----------------------------");		
		}else {
			throw new BusinessException("Account Type  doesn't match ....\nTry Again !! ...");
		}
		
		return accountTypeChoice;
	}
	

	public static BankAccountRegister askBankAccountDetail(CustomerInfo customerinfo) throws BusinessException{
		
		BankAccountRegister bankAccountRegister = new BankAccountRegister();		
		/* 
		 * Account Number inserted by Auto increatement by Database
		 * Format 7 digit integer length
		 */
		
		/* Before create Any Bank Account 
		 * Asking Account Type
		 * 1 - Checking Account
		 * 2 - Saving Account
		 * 3 - EXIT
		 */
		
		bankAccountRegister.askAccountType();
		
		int accoutType = bankAccountRegister.verifyAccountType();
		
		if(accoutType != 3) {
			
			bankAccountRegister.setAccountType(accoutType);
				logger.trace("AccoutType:" + accoutType);
						
				/* asking customer personal information */
						
				if(accoutType == 1) {
					System.out.println("Welcome to Checking Account");
					System.out.println("-----------------------------");
					bankAccountRegister.setAccountName("Checking Account");
					bankAccountRegister.setInterest(0.1);
				}else if(accoutType == 2) {
					System.out.println("Welcome to Saving Account");
					System.out.println("-----------------------------");
					bankAccountRegister.setAccountName("Saving Account");
					bankAccountRegister.setInterest(2);
							
				}else {
					bankAccountRegister.setAccountName("None");
					bankAccountRegister.setInterest(0);
				}
		
						
						
				/* Asking Customer personal information */
				try {
					
 		    	    int userId = bankServiceDAO.getUserId(LogInModel.getUsername());
					int customerId = bankServiceDAO.getCustomerId(userId);
					
					if(customerId == 0 ) {
						System.out.println("Please enter all required information ");
						System.out.println("--------------------------------------");
						System.out.println();
					    customerinfo.askCustomerDetail();
					}
					
				} catch (BusinessException e) {
					logger.fatal(e.getMessage());
				}
						
						
						
				/* setting balance and opening balance */
						
				logger.fatal("Please enter Opening Balance Amount : $");
				double openingBalance = Double.parseDouble(scanner.nextLine());
				if(openingBalance > 0) {
					bankAccountRegister.setBalance(openingBalance);
					bankAccountRegister.setOpeningBalance(openingBalance);
					logger.trace("opening Balance :$" + bankAccountRegister.getOpeningBalance() );
					logger.trace("Balance :$" +bankAccountRegister.getBalance() );
				}else {
							
					System.out.println("Negetive Amount isn't allowed to open Bank Account !! ...");
					bankAccountRegister.setBalance(0);
					bankAccountRegister.setOpeningBalance(0);
				}
						
						
				// opended Date
						
				//setDateOpened(new Date());
						
						
			   /*
			    * By Default it's 1
			    * 1 - active 
				* 2 - Suspend
			    * 3 - Closed
			    * Employee User have authority to manipulate this status with  proper valid Reasons
			    */
						
				bankAccountRegister.setAccountStatus(1);
						
				/*
				 * On this Bank Application have only one branch now 
				 * format State and Number(4 digit)  e.g  IL0001
				 */
						
				bankAccountRegister.setBranchLoc("IL0001");
						
						
			    /* retrieving customer id 
			     * 			
			     */
			
//			    	int userId = bankServiceDAO.getUserId(LogInModel.getUsername());
//					int customerId = bankServiceDAO.getCustomerId(userId);
//					
//	                if(customerId != 0) {
//						setCustomerId(customerId);
//					}else {
//						
//						throw new BusinessException("Bank Account cann't created without Customer ID and User ID \n "
//								+ "Please Sign Up first \n"
//								+ "THANK YOU");
//					    	
//					}
			    	
			 
				
						
						
						
		}else {
			
			System.out.println("Sorry !!! You didn't Choose any Account Type !!! ... ");
			System.out.println("Please Type Again...");
			System.out.println();
		}
		
		
		return bankAccountRegister;
					
	}
	
	
	
	public void displayAccountDeatails() {
		
		System.out.println();
		//System.out.println("Account Details of : " + getCustomerAccountNum() );
		//System.out.println("-----------------------------");
		logger.fatal( "[ Account Number :" + getCustomerAccountNum() +", Account Name :" + getAccountName() + 
				            ", Account Balance :$" + getBalance()+ " ]") ;
//		logger.trace(getCustomerAccountNum());
//		logger.trace(getAccountName());
//		logger.trace(getBalance());
		//System.out.println();
		
	}
   
	
	



	@Override
	public String toString() {
		return "[Customer Account Number =" + customerAccountNum + ", Balance=$" + balance
				+ ", Opening Balance=$" + openingBalance + ", Account Name=" + accountName + ", Customer Id =" + customerId
				+ ", DateOpened=" + dateOpened + ", Interest=" + interest + ", Account Status=" + accountStatus
				+ ", Account Type=" + accountType + ", Branch Location =" + branchLoc + "]";
	}
	





	
	
	
	

}
