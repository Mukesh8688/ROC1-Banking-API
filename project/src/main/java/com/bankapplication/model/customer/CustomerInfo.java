package com.bankapplication.model.customer;

import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankapplication.dao.BankServicesDAOInterface;
import com.bankapplication.dao.impl.BankServicesDAOImpl;
import com.bankapplication.exception.BusinessException;
import com.bankapplication.model.LogInModel;
import com.bankapplication.model.account.BankAccountRegister;

public class CustomerInfo {
	
	// DAO 
	
	private static BankServicesDAOInterface getbankuserid = new BankServicesDAOImpl();
	
	//log4j
		private static Logger logger = Logger.getLogger(BankAccountRegister.class);
		
	//Scanner
		
     Scanner scanner = new Scanner(System.in);
     
     
    // private variables declaration	
	private int customerId;
	private String firstName;
	private String lastName;
	private String addressStreetName;
	private String city;
	private String state;
	private String zipCode;
	private String phoneNumber;
	private String SSN;
	private Date joinDate;
	private int customerRegisterId;
	
	//constructor
	
	public CustomerInfo(){
		
	}

	public CustomerInfo(int customerId,String firstName, String lastName, String addressStreetName, String city, String state,
			String zipCode, String phoneNumber, String SSN, Date joinDate, int customerRegisterId) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.addressStreetName = addressStreetName;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.SSN = SSN;
		this.joinDate = joinDate;
		this.customerRegisterId = customerRegisterId;
	}
	
	
	// Getter and Setter
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressStreetName() {
		return addressStreetName;
	}

	public void setAddressStreetName(String addressStreetName) {
		this.addressStreetName = addressStreetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String ssn) {
		this.SSN = ssn;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date localDate) {
		this.joinDate = localDate;
	}

	public int getCustomerRegisterId() {
		return customerRegisterId;
	}

//	public void setCustomerRegisterId(int customerRegisterId) {
//		CustomerRegisterId = customerRegisterId;
//	}
	
	public void setCustomerRegisterId(int CustomerRegisterId) {
	     this.customerRegisterId =CustomerRegisterId;
    }

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	
	}
	
	
	
	

		
	public void askCustomerDetail() throws BusinessException {
			
			logger.fatal("Entere First Name :");
			setFirstName(scanner.nextLine().toLowerCase());
			logger.trace("FirstName:" + getFirstName() );
			
			logger.fatal("Entere Surname Name :");
			setLastName(scanner.nextLine().toLowerCase());
			logger.trace("SureName:" + getLastName());
			
			logger.fatal("Address :");
			setAddressStreetName(scanner.nextLine());
			logger.trace("Address:" + getAddressStreetName());
			
			logger.fatal("City :");
			setCity(scanner.nextLine().toLowerCase());
			logger.trace("City:" + getCity());
			
			logger.fatal("State :");
			setState(scanner.nextLine().toLowerCase());
			logger.trace("City:" + getState());
			
			logger.fatal("PhoneNumber :");
			setPhoneNumber(scanner.nextLine().toLowerCase());
			logger.trace("City:" + getPhoneNumber());
			
			logger.fatal("Please 9 digit Social Security Number(SSN): ");
			setSSN(scanner.nextLine());
			logger.trace("City:" + getSSN());
			
			//Taking  java.util Date 
			setJoinDate(new java.util.Date());
			logger.trace("JoiningDate:" + getJoinDate()); 
	
			
			
			/* invoked customer Register Id*/
			int customerRegisterId = 0;
			
			customerRegisterId = getbankuserid.getUserId(LogInModel.getUsername());
		
			if(customerRegisterId != 0) {
			    setCustomerRegisterId(customerRegisterId);
			}else {
				throw new BusinessException(" Customer must be registered  before  open Account !!! \n"
						+ "please contact local Agent Or please Sign Up \n"
						+ "THANK YOU");
			}
			logger.trace("Customer_RegisterId:" + customerRegisterId);
			
			
			
			logger.fatal("Enter ZipCode :");
			setZipCode(scanner.nextLine());
			logger.trace("ZipCode:" + getZipCode()); 
			
				
			////
		}

	@Override
	public String toString() {
		return "[Customer ID=" + customerId + ", First Name=" + firstName + ", Last Name=" + lastName
				+ ", Address =" + addressStreetName + ", City=" + city + ", State=" + state + ", ZipCode="
				+ zipCode + ", Phone Number=" + phoneNumber + ", SSN=" + SSN + ", Join Date=" + joinDate
				+ ", Customer RegisterId=" + customerRegisterId + " ]";
	}


	
	
	
	
	
	
	

	
	

}
