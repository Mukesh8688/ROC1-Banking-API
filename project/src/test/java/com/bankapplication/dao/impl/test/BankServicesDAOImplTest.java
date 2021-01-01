package com.bankapplication.dao.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bankapplication.dao.BankServicesDAOInterface;
import com.bankapplication.dao.impl.BankServicesDAOImpl;
import com.bankapplication.exception.BusinessException;

class TestBankServicesDAOImpl {
	
	private static BankServicesDAOInterface bankServicesDAOImpl ;
	
	
	
	@BeforeAll
	public static void setUpbankServicesDAOImpl() {
		bankServicesDAOImpl = new BankServicesDAOImpl();
	}
	
	
    // testing ResiterUsername
	
	@Test
	void testRegisterUsername() throws BusinessException {
		String username = "preet";
		String password = "pass";
		String email = "preet@gmail.com";
		
		int n  = bankServicesDAOImpl.registerUsername(username, password,email);
		assertEquals(1, n);
	}
	
   // Validate same username and email
   // 
	
	@Test
	void testNegativeRegisterUsername() throws BusinessException {
		String username = "preet";
		String password = "pass";
		String email = "preet@gmail.com";
		
		int n  = bankServicesDAOImpl.registerUsername(username, password,email);
		assertEquals(1, n);
	}
	
	

//	@Test
//	void testIsVerifyUsernamePassword() throws BusinessException {
//	  
//		List<RigerterModel> isValide = bankServicesDAOImpl.getUserRegisterDetail("mukesh", "pass");
//		assertTrue(isValide);
//     
//	}
//	
//	
//	@Test
//	void testIsVerifyUsernamePasswordNegative() throws BusinessException {
//	    
//		boolean isValide = bankServicesDAOImpl.getUserRegisterDetail("mukesh", "1111");
//		assertFalse(isValide);
//     
//	}

}
