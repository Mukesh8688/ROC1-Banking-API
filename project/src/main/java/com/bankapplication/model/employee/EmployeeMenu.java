package com.bankapplication.model.employee;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankapplication.exception.BusinessException;
import com.bankapplication.model.customer.CustomerMenu;
import com.bankapplication.model.customer.CustomerViewAccBalance;
import com.bankapplication.services.BankServicesInterface;
import com.bankapplication.services.impl.BankSerciesInterfaceImpl;

public class EmployeeMenu {
	
	/* create Bank Services Interface object to access bank services */
	
	private static BankServicesInterface bankServicesInterface = new BankSerciesInterfaceImpl();

	
	private Scanner scanner = new Scanner(System.in);	
	
	/* create object for log4j */
	
	private static Logger logger = Logger.getLogger(CustomerMenu.class);
	

	
	
	public EmployeeMenu() {

	}
	
	public void displayEmployeeMenu() {
		 
		 
		int menuEmployeeChoice = 0;
	    
	    
		  do {   
			   
		    	 System.out.println(); 
		    	 logger.fatal(" Employee Section Menu ");
		    	 logger.fatal("=============================");
		    	 logger.fatal("1) View Account Details. ");
		    	 logger.fatal("2) View log of all transaction ");
		    	 logger.fatal("3) Update Customer's Account Status.");
		    	 logger.fatal("4) EXIT ");
		    	 logger.fatal("==============================");
		    	 logger.fatal("Please enter appropriate choice(1-4) :) ");
			     
			     
			     // try catch for Number format Exception
			     try {
			    	 menuEmployeeChoice = Integer.parseInt(scanner.nextLine());
			     }catch(NumberFormatException e) {
			    	 
				    System.out.println();
				    logger.fatal("You didn't choose any option !!!");
			    
			    	 menuEmployeeChoice = 0;
			     }
			     
			     
						    
			     /*
			      * switch used for Bank Services Menu option
			      */
				 switch (menuEmployeeChoice) {
					
				
				 case 1:
			            
					 
					 CustomerViewAccBalance customerviewAccBalance = new CustomerViewAccBalance();
					 int byAccViewChoice = 0;
					 
					 try {
						byAccViewChoice = customerviewAccBalance.askAccoutParametersByEmployee();
						bankServicesInterface.getAllAccountInfoByEmployee(byAccViewChoice, customerviewAccBalance.getAccountNumber());
						
					} catch (BusinessException e) {
						logger.fatal(e.getMessage());
					}					
								
				     break;
			      
							
								
						 
				 case 2:
					 // for view all transaction by account number
							 
					
					 
					 logger.fatal("View all transactions by account number:");
					 logger.fatal("------------------------------------------");
					 logger.fatal("Enter Account Number : ");
					 int accountNumber = Integer.parseInt(scanner.nextLine());
					 
					 try {
						 
						 
						 bankServicesInterface.getAllTransactionByAccountNumber(accountNumber);
						 
						 
					 }catch(BusinessException e) {
						 
						 logger.info(e.getMessage());
					 }
						 
	
					 
				     break;
										
	
						 
				 case 3:  
					 
					 // Approve or Reject Customer's Account
					 
				     
					 logger.fatal("Bank Account Status Admin Section :");
					 logger.fatal("----------------------------------");
					 logger.fatal("Enter Account Number : ");
					 int accountNumberForStatus = Integer.parseInt(scanner.nextLine());
					 
					 try {
						 
						 
						 bankServicesInterface.changeBankAccountStatus(accountNumberForStatus);
						 
						 
						 
					 }catch(BusinessException e) {
						 
						 logger.info(e.getMessage());
					 }
						    
						  
										
							
				     break;	
							 
							 
							 
							 
						 
				 case 4:
							 
				
					 // EXIT from Employee  Menu		
					 System.out.println("\n");
					logger.fatal("<<=== Employee Menu End ===>");
					
					
					
				    break;	 
	
									
						 
				 default:
							 
							
					 System.out.println();
							
					 logger.fatal("SORRY !!! .. This is INVALIDE choice Menu Number !!");
					
					 System.out.println();
				
										
							
					 break;
									
						 
				 }
				    		
		     
		  }while(menuEmployeeChoice != 4);
		  
	
	
	 }


}
