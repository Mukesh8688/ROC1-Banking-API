package com.bankapplication.businessinterface.services.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bankapplication.exception.BusinessException;
import com.bankapplication.model.customer.CustomerInfo;
import com.bankapplication.model.customer.CustomerViewAccBalance;
import com.bankapplication.services.BankServicesInterface;
import com.bankapplication.services.impl.BankSerciesInterfaceImpl;

public class TestBankAppliccationBusinessInterfaceServices {
	
	
	private static BankServicesInterface bankServicesInterface;
	
	@BeforeAll
	public static void setUpBankServicesInterfaces() {
		bankServicesInterface = new BankSerciesInterfaceImpl();
	}
	
	
	// 1. Verify username and password with valid data
    // 2. Expected value - True 
	
    @Test
    void testIsVerifyUsernamePasswordPositive() throws BusinessException {
    	String username ="preet";
    	String password = "preet";
    			
       boolean isVerify = bankServicesInterface.isVerifyUsernamePassword(username, password);
    	assertEquals(true, isVerify);
    }
    
	
	// 2. Verify username and password with invalid data
	//  Expected value - true 
	
    @Test
    void testIsVerifyUsernamePasswordNegative() throws BusinessException {
    	String username ="preet";
    	String password = "preet1";
    	
      try {	
    	  
       boolean isVerify = bankServicesInterface.isVerifyUsernamePassword(username, password);
       
      }catch(BusinessException e) {
    	  
    	  System.out.println(e.getMessage());
      }
    	
    }
    
    // 3. getting userid with valid value
    // expected value - true
    
    @Test 
    void testGetUserTypePositive() throws BusinessException{
    	
    	String username ="preet";
    	int user = bankServicesInterface.getUserType(username);
    	assertEquals(1, user);
    	
    }
    
    
    //4. validate  Customer requirements with valid data
    // expected value - true
    
    @Test 
    void testVerifyBankAccountRequirementsPositive() throws BusinessException{
    	
    	CustomerInfo customerinfo;
    	
    	customerinfo = new CustomerInfo(10, "mukesh", "chaudhary", "567 kenmroe", "chicago", "IL", "60660", "7734928688", "773243931", new Date(), 23);
    	
    	boolean isVarify = bankServicesInterface.verifyBankAccountRequirements(customerinfo);
    	assertEquals(true, isVarify);
    	
    }
    
    
    
    //5. validate  Customer requirements with invalid data
    // phone number - 9 digit
    // expected value - true
    
    @Test 
    void testVerifyBankAccountRequirementsNegative() throws BusinessException{
    	
    	CustomerInfo customerinfo;
    	
    	customerinfo = new CustomerInfo(10, "mukesh", "chaudhary", "567 kenmroe", "chicago", "IL", "60660", "773492868", "773243931", new Date(), 23);
    	
    	boolean isVarify = bankServicesInterface.verifyBankAccountRequirements(customerinfo);
    	assertEquals(true, isVarify);
    	
    }
    
    
    //5. validate viewAccountBalance()
    // expected value 
    
    @Test
    void testVarifyViewAccountBalance() throws BusinessException {
    	
    	int accountNumber = 1000003;
    	int customerId = 1002;
    	CustomerViewAccBalance customerViewAccBalance = new  CustomerViewAccBalance(accountNumber,customerId);
    	int choice = 1;
    	
    	bankServicesInterface.viewAccountBalance(customerViewAccBalance, choice);
    	
    	
    }
    
    //6. Validate get all account Information via Employee Menu
	// ByAccViewChoice  variable  : 1 - for By AccountNumber 
	//                              2 - for All Account
    
    @Test
    void testGetAllAccountInfoByEmployee() throws BusinessException {
      
    	int accountNumber = 1000003;
    	// 1 - for By AccountNumber 
    	// 2 - for All Account
    	int byAccViewChoice = 1 ;  
    	
    	bankServicesInterface.getAllAccountInfoByEmployee(byAccViewChoice, accountNumber);
    	
    }
    
    
    // 7.  Validate  - Get All transactions by Account Number
    
    @Test
    void testGetAllTransactionByAccountNumber() throws BusinessException{
    	int accountNumber = 1000003;
    	bankServicesInterface.getAllTransactionByAccountNumber(accountNumber);
    	
    }
    
    
    //8. Validate - Change Bank Account Status by account number via employee section menu
    
    @Test
    void testChangeBankAccountStatus() throws BusinessException{
    	
    	int accountNumber = 1000003;
    	
    	bankServicesInterface.changeBankAccountStatus(accountNumber);
    }
    
    
    //9. Validate -- create employee user profile by admin menu
    // Usertype defined : 1 - for Customer  Type username
    //                      2 - Employee Type Username
    
    @Test
    void testCreateEmployeeUserProfile() throws BusinessException{
    	
    	String username = "maruti";
    	String password = "ram";
    	String email = "maruti@gmail.com";
    	int usertype = 2;
    	
    	bankServicesInterface.createEmployeeUserProfile(username, password, email, usertype);
    	
    }
    
  
    
    
    
}
