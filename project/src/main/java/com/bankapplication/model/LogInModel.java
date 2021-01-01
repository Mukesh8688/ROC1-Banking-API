package com.bankapplication.model;

import com.bankapplication.dao.BankServicesDAOInterface;
import com.bankapplication.dao.impl.BankServicesDAOImpl;
import com.bankapplication.exception.BusinessException;
import com.bankapplication.model.admin.AdminSection;
import com.bankapplication.model.customer.CustomerMenu;
import com.bankapplication.model.employee.EmployeeMenu;
import com.bankapplication.services.BankServicesInterface;
import com.bankapplication.services.impl.BankSerciesInterfaceImpl;

import java.util.Scanner;

import org.apache.log4j.Logger;



public class LogInModel {
	
	//log4j
    public static Logger logger = Logger.getLogger(BankSerciesInterfaceImpl.class);
    
    
	// DAO Services
	public static  BankServicesDAOInterface bankServiceDAO = new BankServicesDAOImpl();
	
   //Scanner
	
	public static Scanner scanner = new Scanner(System.in);
	
	
	 // create object to interact BankServices
	private static BankServicesInterface bankServicies = new BankSerciesInterfaceImpl();
	
	
	// for load User information
    private static RegisterModel registermodel = new RegisterModel();
    
    
    /* Method to get  username */
    
    public static String usernamePara = "";
	
    public static String getUsername() {
    	
    	usernamePara = registermodel.getUsername();
    	
		return usernamePara;
    
    }
     
	 
	 /*
	  * LoginBankApp method
	  * Used for enter Bank Application 
	  * Verify Username and Password
	  * Verify Usertype too like Bank Customer , Bank Employee and Admin
	  */
	 
	 public void loginBankApp() {
		 
		 // asking Username and Password login section
		 registermodel.askingUsernamePassword();
		 
		 
				try {
					
					
					if(bankServicies.isVerifyUsernamePassword(registermodel.getUsername(), registermodel.getPassword())) {
						   
						   System.out.println();
					       System.out.println("You Logged in  Successfully !! ...");
					       System.out.println();
						
						   int menuUsertype = bankServicies.getUserType(registermodel.getUsername());
						   
						   
						   
						   /*
						    * switch used for Usertype
						    * 1 For Bank Customer
						    * 2 For Bank Employee
						    * 3 For admin
						    */
						   switch (menuUsertype) {
						   
								
						   case 1: //  Bank Customer
							 
							   
							   CustomerMenu customerMenu = new CustomerMenu();
							   customerMenu.displayCustomeServicerMenu();
							   
							   break;
									
									
									
								
						   case 2: // Bank Employee
			
                               EmployeeMenu employeeMenu = new EmployeeMenu();
                               System.out.println(" ******** WELCOME "+ registermodel.getUsername().toUpperCase()+" ********** ");
                               employeeMenu.displayEmployeeMenu();
							   
									
									
							   break;
									
								
						   case 3: // For Admin
							   
							  
					    	   AdminSection adminSection = new AdminSection();
							   
							  
							    	
							   int adminChoice = adminSection.displayAdminMenu();
							   // asking Employee Information
							   adminSection.displayRequirementForEmployee(adminChoice);
							   if(adminSection != null) {
							      adminSection.createEmployeeUserProfile(adminSection);
							    }
							    	
							  
							   
									
									
							   break;	
		
								
						   default:
							   
							    System.out.println("Sorry!!! Username or Password isn't matched ... Please try again...\n"
								   		+ "OR... Please Contanct Bank Employee !!! \n"
								   		+ "THANK YOU");
							   
							   break;
							
									
								
						   }
						
							
							
								
						
	   
					  }else {
						  
						  System.out.println();
						  throw new BusinessException(" Username or Password isn't match ...Please try again... \n "
						  		+ "If you aren't existing Bank Customer ... \n "
						  		+ "PLEASE Sign up first... CHOOSE 2 OPTION... \n"
						  		+ " *** THANK YOU ***" );
					  }
				} catch (NumberFormatException | BusinessException e1) {
					  System.out.println();
					  System.out.println(e1.getMessage());
				}
				
				
	 }			
	

}
