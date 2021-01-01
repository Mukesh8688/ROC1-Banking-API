package com.bankapplication.model;

import java.util.Scanner;

import org.apache.log4j.Logger; 

public class RegisterModel {
	

	
	//logging all file
	
	private static Logger logger = Logger.getLogger(RegisterModel.class);
    //private static Logger loggerFile = Logger.getLogger(RegisterModel.class);
	
	
	private int id;
	private String username;
	private String password;
	private String email;
	
	/* passing integer number 1 for customer , 
	 * 2 for employee and 3 for admin
	 */
	private int userType;  
	
	
	// create scanner object
	// taking inputs from console
	
	Scanner scanner = new Scanner(System.in);
	
	
	// constructor
	public RegisterModel() {
		
	}
	
	
	public RegisterModel(int id ,String username,String password,String email,int userType) {
		this.id = id ;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userType = userType;
	}
	
	
	// getter and shetter
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id ) {
		
		this.id = id ;
	}
	
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
    
    
    public int getUserType() {
    	return userType;
    }
    
    public void setUserType(int userType) {
    	this.userType = userType;
    }
    
    
    
    
    
    public void askingUsernamePassword() {
    	
    	
    	
//        System.out.println("Please Enter Username:");
//        setUsername(scanner.nextLine());
//        System.out.println("Please Enter Password:");
//        setPassword(scanner.nextLine());
    	
    	
    	System.out.println();
    	logger.fatal("Please Enter Username:");
    	setUsername(scanner.nextLine());
    	logger.trace("entered username:" + getUsername());
    	logger.fatal("Please Enter Password:");
        setPassword(scanner.nextLine());
        logger.trace("entered password:"+getPassword());
       
    }
    
   public void askingUsernamePasswordEmail() {
    	
    	
//    	System.out.println();
//        System.out.println("Please Enter Username:");
//        setUsername(scanner.nextLine());
//        System.out.println("Please Enter Password:");
//        setPassword(scanner.nextLine());
//        System.out.println("Please Enter Email: ");
//   	    setEmail(scanner.nextLine());
	   
	   
   	   System.out.println();
   	   logger.fatal("Please Enter Username:");
       setUsername(scanner.nextLine());
   	   logger.trace("entered username:" + getUsername());
   	   logger.fatal("Please Enter Password:");
       setPassword(scanner.nextLine());
       logger.trace("entered password:"+getPassword());	
       logger.fatal("Please enter Email:");
       setEmail(scanner.nextLine());
       logger.trace("entered email:" + getEmail());
       
    	
    }
    
    
    
}
