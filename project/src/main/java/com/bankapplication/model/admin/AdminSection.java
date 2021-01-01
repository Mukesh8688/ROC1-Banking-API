package com.bankapplication.model.admin;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankapplication.dao.BankServicesDAOInterface;
import com.bankapplication.dao.impl.BankServicesDAOImpl;
import com.bankapplication.exception.BusinessException;
import com.bankapplication.services.BankServicesInterface;
import com.bankapplication.services.impl.BankSerciesInterfaceImpl;

public class AdminSection {
	
	
	//log4j
    private static Logger logger = Logger.getLogger(BankSerciesInterfaceImpl.class);
	
    // BANK SERVICES DAO
	BankServicesDAOInterface bankServiceDAO = new BankServicesDAOImpl();
	
	// Bussiness Interface 
	
	private static BankServicesInterface bankServicesInterface = new BankSerciesInterfaceImpl();
	
   //Scanner
	
	Scanner scanner = new Scanner(System.in);
	
	
	//  Declare Instance Variable
	
	private String username;
	private String password;
	private String email;
	private int usertype;
	
	//constructor
	public AdminSection() {
	
	}
	
	
	
	public AdminSection(String username, String password, String email, int usertype) {
		
		this.username = username;
		this.password = password;
		this.email = email;
		this.usertype = usertype;
	}


	
	
	//Setter and Getter
	

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getUsertype() {
		return usertype;
	}



	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}



	public int displayAdminMenu() {
		
		int adminMenu = 0;
		System.out.println();
		logger.fatal("Admin Menu");
		logger.fatal("===============================");
		logger.fatal("1)Create Employee User Profile.");
		logger.fatal("2)Update Invalid Transaction.");
		logger.fatal("3)Exit");
		logger.fatal("================================");
		logger.fatal("Please enter appropriate choice[1-3]:");
		
		adminMenu = Integer.parseInt(scanner.nextLine());
		
		return adminMenu;
	}
	
	
	
	public void displayRequirementForEmployee(int adminMenu) throws BusinessException {
		
		if(adminMenu == 1) {
			System.out.println();
			logger.fatal("Enter employee Username:");
			setUsername(scanner.nextLine());
			logger.info("employeeUsername:" + getUsername());
			logger.fatal("Enter employee Password:");
			setPassword(scanner.nextLine());
			logger.info("employeePassword:" + getPassword());
			
			logger.fatal("Enter employee email address:");
			setEmail(scanner.nextLine());
			logger.info("employeeUsername:" + getEmail());
			
			setUsertype(2);
			
			
		}else {
			throw new BusinessException("Please try again ...");
		}

		
	}
	
	public void createEmployeeUserProfile(AdminSection adminSection) {
		
		int status =0 ;

		
		try {
			
			if(adminSection.getUsername().length() < 4) {
				
				throw new BusinessException("Username character length should be more than 3 ...\nplease try again...");
				
			}
			
			if(adminSection.getUsername().length() > 3) {
			   // passing  userType = 2 as a employee to make user profile.
				
			   status = bankServicesInterface.createEmployeeUserProfile(adminSection.getUsername(), adminSection.getPassword(), adminSection.getEmail(), adminSection.getUsertype());
			   
			}
			
		} catch (BusinessException e) {
		   
			logger.fatal(e.getMessage());
		}
		
		
		if(status == 1) {
			logger.fatal("Employee Account is created Successfully ...");
		}
		
	}
	
	

}
