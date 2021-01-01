package com.bankapplication.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankapplication.exception.BusinessException;
import com.bankapplication.model.LogInModel;
import com.bankapplication.model.SignUpModel;
import com.bankapplication.model.customer.CustomerMenu;



public class BankMain {
	
	
	/* create object for log4j */
	
	private static Logger logger = Logger.getLogger(CustomerMenu.class);


	public static void main(String[] args)  {
		
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println();
		System.out.println("Welcome Mukesh's Bank Application Version 1.0");
		System.out.println("==============================================");
		
		
	    int mainMenuChoice = 0; 
     	
		do {
			System.out.println();
	        logger.fatal(" **** MAIN MENU **** ");		
	        logger.fatal("================================");	
	        logger.fatal("1) Log in ");
	        logger.fatal("2) Sign up ");
	        logger.fatal("3) Admin");
	        logger.fatal("4) EXIT");
	        logger.fatal("================================");
	        logger.fatal("Please enter appropriate choice(1-4) :) ");
		
		 
		// to prevent from enter event from keyboard
		    try {
		    	mainMenuChoice = Integer.parseInt(scanner.nextLine());
		    	logger.trace("enteredOption:" +mainMenuChoice);
		    }catch(NumberFormatException e) {
		    	System.out.println();
		    	logger.fatal("You didn't choose any option !!! ...");
		    	mainMenuChoice = 0;
		    }
		
		    
			
			switch (mainMenuChoice) {
			
			  case 1: 
				   
				  /*
				   * this option for log in section
				   * For Bank Customer
				   * For Bank Employee
				   * For Admin
				   */
				  
				   LogInModel login = new LogInModel();
				   login.loginBankApp();
				   
					break ;	
						
				
		     case 2: 
	
		          /* For Sign Up Section 
		           * Register Customers' username ,password, email 
		    	  */
		    	 
		    	 
		    	 SignUpModel signup = new SignUpModel();
		    	 try {
		    		 
					signup.bankCustomerSignupMethod();
					
				  } catch (BusinessException e) {
					  
					  System.out.println();
					  logger.fatal(" REGISTRATIOM FAIL !!! ... ");
					  logger.fatal(e.getMessage());
					  System.out.println();
					
	
					
				  }
		    	 
		    	
				break;
				
				
		     case 3:
		    	
		    	   System.out.println();
		    	   logger.fatal("==== Warning !!! This option is for admin User only =====");
		    	   System.out.println();
				   logger.fatal("If you are not admin user ... \nPlease type 8 to exit this section ... \nOR Enter any Number to continue ...");
				   
		           int admin = 0;
		           
		           try {

		        	   admin = Integer.parseInt(scanner.nextLine());
		           }catch(NumberFormatException e){
		        	   admin = 0;
		           }
		           
		           if(admin !=8) {
		        	   
		        	  System.out.println();
	
				      LogInModel loginAdmin = new LogInModel();
				      loginAdmin.loginBankApp();
						    
					
		        	   
		           }else {
		        	   
		        	   break;
		           }
		        	   
		
		    	 
		    	 break;
		    	 
		    	 
		    	 
		     case 4: // number 4 for EXIT system
		    	 System.out.println("\n");
		    	 logger.fatal("<<<=== THANK YOU FOR USING BANK APPLICATION VERSION 1.0 ===>>");
		    	 break;
		    	 
		    	 
	
			default:
				System.out.println();
				logger.fatal("Please Choose Right option ... \nOr type 4 to exit from Application ... \n\n===THANK YOU===");
				break;
			}

		
		
        }while( mainMenuChoice != 4); 
		
		
		
		scanner.close();

	}
	
	

}
