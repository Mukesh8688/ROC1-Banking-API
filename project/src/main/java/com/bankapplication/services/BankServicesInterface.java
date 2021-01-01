package com.bankapplication.services;

import com.bankapplication.exception.BusinessException;
import com.bankapplication.model.account.BankAccountRegister;
import com.bankapplication.model.customer.BalanceTranfer;
import com.bankapplication.model.customer.CustomerInfo;
import com.bankapplication.model.customer.CustomerViewAccBalance;

public interface BankServicesInterface {
	
	public int registerUsername(String username,String password,String email) throws BusinessException;
	
	public boolean isVerifyUsernamePassword(String username,String password) throws BusinessException;
	
	public int getUserType(String username) throws BusinessException;
	
	public boolean applyNewBankAcoount(CustomerInfo customerinfo,BankAccountRegister bankAccountRegister) throws BusinessException;
	
	public void viewAccountBalance(CustomerViewAccBalance customerViewAccBalance, int choice ) throws BusinessException;
	
	public int bankTransaction(int accountTypeChoice,int transactionType) throws BusinessException;
	
	
	
	public int balanceTranfer(BalanceTranfer balancetransfer) throws BusinessException;
	
	
	public boolean verifyBankAccountRequirements(CustomerInfo customerinfo) throws BusinessException;
	
	public boolean verifyBankAccountRequirements(BankAccountRegister bankAccountRegister) throws BusinessException;
	
	public void getAllAccountInfoByEmployee(int byAccViewChoice, int accountNumber) throws BusinessException;
	
	public void getAllTransactionByAccountNumber(int accountNumber) throws BusinessException;
	
	public int changeBankAccountStatus(int accountNumber) throws BusinessException;
	
	public int createEmployeeUserProfile(String username, String password , String email, int usertype) throws BusinessException;

	
}
