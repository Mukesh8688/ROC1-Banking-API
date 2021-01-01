package com.bankapplication.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bankapplication.dao.BankServicesDAOInterface;
import com.bankapplication.dao.dbutil.BankAccountQueries;
import com.bankapplication.dao.dbutil.PostgresDBConncetion;
import com.bankapplication.exception.BusinessException;
import com.bankapplication.model.LogInModel;
import com.bankapplication.model.RegisterModel;
import com.bankapplication.model.account.AccountTransaction;
import com.bankapplication.model.account.BankAccountRegister;
import com.bankapplication.model.customer.CustomerInfo;


public class BankServicesDAOImpl implements BankServicesDAOInterface{
	
	// Log4j
	private static Logger logger = Logger.getLogger(BankServicesDAOImpl.class);
	
	
	@Override
    public int registerUsername(String username, String password, String email) throws BusinessException {
    	
    	int c = 0 ;
    	
		try(Connection connection = PostgresDBConncetion.getConnection()){
			
		
			
			String sql = BankAccountQueries.REGISTER_USER;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username.toLowerCase());   // not Case Sensitive
			preparedStatement.setString(2, password);                // Case Sensitive
			preparedStatement.setString(3, email.toLowerCase());  // not case Sensitive
			preparedStatement.setInt(4, 1);
			
			//execute SQL query
			c = preparedStatement.executeUpdate();
			//System.out.println("Query Succefully executed !!");
	
				
			
		}catch(ClassNotFoundException|SQLException e) {
		    System.out.println(e.getMessage());
			
		}
		
		
		return c;
		
		
	}
    
    
    
    @Override
    public List<RegisterModel> getUserRegisterDetail(String username,String password) throws BusinessException{
    	
    	List<RegisterModel> customerRegisterList = new ArrayList<>();
    	
    	try(Connection connection = PostgresDBConncetion.getConnection()){
    		
    		String sql = BankAccountQueries.GET_ALL_REGISTERS_BY_USERNAMEPASSWORD;
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setString(1,username.toLowerCase());
    		preparedStatement.setString(2, password);
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    		
    			RegisterModel registerModel =  new RegisterModel(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"),
						resultSet.getString("email"), resultSet.getInt("usertype"));
    			
    			customerRegisterList.add(registerModel);
    			
    		}
    		
    		if(customerRegisterList.size() == 0) {
    			
    			throw new BusinessException("No User List Found !! ...\nPlease Sign up first... ");
    			
    		}
    		
    	}catch(ClassNotFoundException|SQLException e) {
    		
    		System.out.println(e.getMessage());
    	}
    	
    	
    	return customerRegisterList;
    }
    
    
    @Override
    public int getUserType(String username ) throws BusinessException{
        
    	
    	int userType = 0;
    	
    	try(Connection connection = PostgresDBConncetion.getConnection()){
    		
    		String sql = BankAccountQueries.GET_USERTYPE_USERID_BY_USERNAME;
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setString(1,username.toLowerCase());
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		if(resultSet.next()) {
    			
    			userType = resultSet.getInt("usertype");
    		}else {
    			
    			userType = 0;
    		}
    		
    	}catch(ClassNotFoundException|SQLException e) {
    		
    		System.out.println(e.getMessage());
    	}
    	
    	
    	return userType;
    	
    	
    }
    
    @Override
    public boolean isVerifyExistUsername(String username,String email) throws BusinessException{
    	
    	boolean isVerify = false;
    	
    	
        try(Connection connection = PostgresDBConncetion.getConnection()){
    		
    		String sql = BankAccountQueries.GET_ALL_REGISTERS;
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		//preparedStatement.setString(1,username);
    		//preparedStatement.setString(1,username);
    		
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		//	|| resultSet.getString("email").toLowerCase().equals(email.toLowerCase())
    		
    		while(resultSet.next()) {
    			if(resultSet.getString("username").toLowerCase().equals(username.toLowerCase())
    					|| resultSet.getString("email").toLowerCase().equals(email.toLowerCase())) {
    				
    				isVerify = true;
    				break;
    			}else {
    				
    				isVerify = false;
    				
    			}
    			
    		}
    		
    	}catch(ClassNotFoundException|SQLException e) {
    		
    		System.out.println(e.getMessage());
    	}
    	
    	
    	
    	
    	return isVerify;
    }
    
    @Override
    public int createCustomerProfile(CustomerInfo customerInfo) throws BusinessException{
    	int success = 0;
    	
        try(Connection connection = PostgresDBConncetion.getConnection()){
    		
    		
     		/* query for bank_customers table*/
    		String insertCustomerInfo  =  BankAccountQueries.INSERT_BANK_CUSTOMER;
    		
    		PreparedStatement preparedStatementCustomerInfo = connection.prepareStatement(insertCustomerInfo);
    		preparedStatementCustomerInfo.setString(1,customerInfo.getFirstName().toLowerCase());
    		preparedStatementCustomerInfo.setString(2,customerInfo.getLastName().toLowerCase());
    		preparedStatementCustomerInfo.setString(3,customerInfo.getAddressStreetName().toLowerCase());
    		preparedStatementCustomerInfo.setString(4,customerInfo.getCity().toLowerCase());
    		preparedStatementCustomerInfo.setString(5,customerInfo.getState().toLowerCase());
    		preparedStatementCustomerInfo.setString(6,customerInfo.getPhoneNumber());
    		preparedStatementCustomerInfo.setInt(7,Integer.parseInt(customerInfo.getSSN()));
    		preparedStatementCustomerInfo.setDate(8, new java.sql.Date(new java.util.Date().getTime()));
    		
    		int custRegisterId = getUserId(LogInModel.getUsername());
    		if(custRegisterId !=0) {
    			preparedStatementCustomerInfo.setInt(9,custRegisterId);
    			
    		}else {
    			
    			throw new BusinessException("User ID doesn't Exist !! ... \n Please Sign Up first ... \n"
    					+ "===THANK YOU===");
    		}
    		
    		preparedStatementCustomerInfo.setString(10,customerInfo.getZipCode());
    		
    		
    		
    		int statusInsertCustomerInfo = preparedStatementCustomerInfo.executeUpdate();
    		
    		if(statusInsertCustomerInfo == 1) {
    			success = 1;
    		}else {
    			success = 0;
    		}
        
        }catch(ClassNotFoundException|SQLException e) {
    		
    		System.out.println(e.getMessage());
    	}	
    		
    	
    	
    	return success;	
    }
    
    @Override
    public int createCustomerProfile(CustomerInfo customerInfo,int userId) throws BusinessException{
    	int success = 0;
    	
        try(Connection connection = PostgresDBConncetion.getConnection()){
    		
    		
     		/* query for bank_customers table*/
    		String insertCustomerInfo  =  BankAccountQueries.INSERT_BANK_CUSTOMER;
    		
    		PreparedStatement preparedStatementCustomerInfo = connection.prepareStatement(insertCustomerInfo);
    		preparedStatementCustomerInfo.setString(1,customerInfo.getFirstName().toLowerCase());
    		preparedStatementCustomerInfo.setString(2,customerInfo.getLastName().toLowerCase());
    		preparedStatementCustomerInfo.setString(3,customerInfo.getAddressStreetName().toLowerCase());
    		preparedStatementCustomerInfo.setString(4,customerInfo.getCity().toLowerCase());
    		preparedStatementCustomerInfo.setString(5,customerInfo.getState().toLowerCase());
    		preparedStatementCustomerInfo.setString(6,customerInfo.getPhoneNumber());
    		preparedStatementCustomerInfo.setInt(7,Integer.parseInt(customerInfo.getSSN()));
    		preparedStatementCustomerInfo.setDate(8, new java.sql.Date(new java.util.Date().getTime()));
    	
    		if(userId !=0) {
    			preparedStatementCustomerInfo.setInt(9,userId);
    			
    		}else {
    			
    			throw new BusinessException("User ID doesn't Exist !! ... \n Please Sign Up first ...\n"
    					+ "===THANK YOU===");
    		}
    		
    		preparedStatementCustomerInfo.setString(10,customerInfo.getZipCode());
    		
    		
    		
    		int statusInsertCustomerInfo = preparedStatementCustomerInfo.executeUpdate();
    		
    		if(statusInsertCustomerInfo == 1) {
    			success = 1;
    		}else {
    			success = 0;
    		}
        
        }catch(ClassNotFoundException|SQLException e) {
    		
    		System.out.println(e.getMessage());
    	}	
    		
    	
    	
    	return success;	
    }
    
    
    
    
    
    /* Insert all Account requirements into Bank_Account , Bank_customers, Bank_transactions */
    @Override
    public int createBankAccount( BankAccountRegister bankAccountRegister,int customerId ) throws BusinessException{
    	
    	
    	int success = 0 ;
    	
    	try(Connection connection = PostgresDBConncetion.getConnection()){
    		
    	
    		/*query for bank_account table */
    		String insertBankAccount = BankAccountQueries.INSERT_BANK_ACCOUNT;
    		
    		PreparedStatement preparedSatementBankAccount = connection.prepareStatement(insertBankAccount);
    		preparedSatementBankAccount.setDouble(1,bankAccountRegister.getBalance() );
    		preparedSatementBankAccount.setDouble(2,bankAccountRegister.getOpeningBalance() );
    		preparedSatementBankAccount.setString(3,bankAccountRegister.getAccountName());
    	
    		if(customerId !=0) {
    		  preparedSatementBankAccount.setInt(4, customerId);
    		}else {
    			throw new BusinessException("Customer Detail haven't created yet !!... \n"
    					+ "Please try again ...");
    		}
    		
    		
    	    preparedSatementBankAccount.setDate(5, new Date(new java.util.Date().getTime()));
    		preparedSatementBankAccount.setDouble(6, bankAccountRegister.getInterest());
    		preparedSatementBankAccount.setInt(7,bankAccountRegister.getAccountStatus());
    		preparedSatementBankAccount.setInt(8, bankAccountRegister.getAccountType());
    		preparedSatementBankAccount.setString(9, bankAccountRegister.getBranchLoc());
    		
    		
    		int statusInsertBankAccount =  preparedSatementBankAccount.executeUpdate();
    	
    		
    		
    		
    		

    		
    		
    		/*query for transactoin table */
    		/* TransType 
    		 * 1 - Withdraw
    		 * 2- deposit
    		 */

    		
    		//int statusInsertTransaction = insertTranactionata(2, bankAccountRegister.getBalance(), bankAccountRegister.getCustomerAccountNum());
    		
    		
    		
//    		if(statusInsertBankAccount == 1  && statusInsertTransaction == 1) {
//    			success =1;
//    		}else {
//    			success =0;
//    		}
    		
    		
    		if(statusInsertBankAccount == 1) {
    			success =1;
    		}else {
    			success =0;
    		}
    		
    		
    		
    	
    	
    		
    	}catch(ClassNotFoundException|SQLException e) {
    		
    		System.out.println(e.getMessage());
    	}
    	
	 
     return success;
     
     
    }


	@Override
	public int insertTranactionata(int transType,double amount, int accountNumber) throws BusinessException {
		
		int success = 0;
		
        try(Connection connection = PostgresDBConncetion.getConnection()){
    		
    		String sql = BankAccountQueries.INSERT_BANK_TRANSACTION;
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		
    		/* TransAction should be Withdraw  or Deposit */
    		if(transType == 1){
    		   preparedStatement.setInt(1,1);
    		}else if(transType == 2) {
    		   preparedStatement.setInt(1,2);
    		}else {
    			throw new BusinessException("TransAction should be Withdraw  or Deposit !! ...");
    		}
    		
    		/* Amount  should greater than 0
    		 * 
    		 */
    		
    		if(amount > 0) {
    		  preparedStatement.setDouble(2,amount) ;
    		  double totalBalance = amount + getAccountBalance(accountNumber);
    		  preparedStatement.setDouble(4,totalBalance);
    			
    		}else {
    			
    			throw new BusinessException("Amount  should greater than 0 !!...");
    		}
    		
    		// AccountNumber
    		preparedStatement.setInt(3, accountNumber);
    		
    		
    		
    		//current Transaction Date
    		
    		preparedStatement.setDate(5, new Date(new java.util.Date().getTime()));
    		
    		
    		
    		
    		
    		int c  = preparedStatement.executeUpdate();  // execute Query
    		
    		if(c == 1 ) {
    			
    			success = 1;
    		}else {
    			
    			success = 0;
    		}
    		
    		
    	}catch(ClassNotFoundException|SQLException e) {
    		
    		System.out.println(e.getMessage());
    	}
		

		return success;
	}
    
    
	@Override
	public int getUserId(String username) throws BusinessException{
		
		int userId = 0;
		
        try(Connection connection = PostgresDBConncetion.getConnection()){
    		
    		String sql = BankAccountQueries.GET_USERTYPE_USERID_BY_USERNAME;
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setString(1,username.toLowerCase());
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		if(resultSet.next()) {
    			
    			userId = resultSet.getInt("id");
    		}else {
    			
    			userId = 0;
    		}
    		
    	}catch(ClassNotFoundException|SQLException e) {
    		
    		System.out.println(e.getMessage());
    	}
    	
		
		
		return userId;
				
	}
	
	
	

	
	@Override
	public int getCustomerId(int userId) throws BusinessException{
		
		int customerId = 0;
		
		 try(Connection connection = PostgresDBConncetion.getConnection()){
	    		
	    		String sql = BankAccountQueries.GET_CUSTOMERID_BY_USERID;
	    		
	    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    		preparedStatement.setInt(1, userId);
	    		
	    		
	    		ResultSet resultSet = preparedStatement.executeQuery();
	    		
	    		if(resultSet.next()) {
	    			
	    			customerId = resultSet.getInt("customerid");
	    		}else {
	    			
	    			customerId = 0;
	    		}
	    		
	    	}catch(ClassNotFoundException|SQLException e) {
	    		
	    		System.out.println(e.getMessage());
	    	}
		
		return customerId;
		
		
	}
	
	
	@Override
	public int getCustomerIdByAccountNumber(int accountNumber) throws BusinessException{
		
		int customerId =0;
		
		try(Connection connection = PostgresDBConncetion.getConnection()){
    		
    		String sql = BankAccountQueries.GET_ACCOUNTINFO_BY_ACC_NUM;
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1, accountNumber);
    		
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		if(resultSet.next()) {
    			
    			customerId = resultSet.getInt("customers_customerid");
    		}else {
    			
    			customerId = 0;
    		}
    		
    	}catch(ClassNotFoundException|SQLException e) {
    		
    		System.out.println(e.getMessage());
    	}
		
		
		
		return customerId;
	}
	
	@Override
	public int getAccountNumberByCustomerId(int customerId) throws BusinessException{
		
        int accountNumber =0;
		
		try(Connection connection = PostgresDBConncetion.getConnection()){
    		
    		String sql = BankAccountQueries.GET_ACC_NUMBER_BY_CUSTOMERID;
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1, customerId);
    		
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		if(resultSet.next()) {
    			
    			accountNumber = resultSet.getInt("accountnumber");
    		}else {
    			
    			accountNumber = 0;
    		}
    		
    	}catch(ClassNotFoundException|SQLException e) {
    		
    		System.out.println(e.getMessage());
    	}
		
		
		
		return accountNumber;
		
		
	}
	
	
	
	@Override
	public boolean isVerifyAccountNumByCustomerId(int accountNumber,int customerId) throws BusinessException{
		boolean isVerity = false;
		
		int accountNumByCustomerId = 0;
		
        try(Connection connection = PostgresDBConncetion.getConnection()){
    		
    		String sql = BankAccountQueries.GET_ACC_NUMBER_BY_CUSTOMERID;
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1, customerId);
    		
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		if(resultSet.next()) {
    			
    			accountNumByCustomerId = resultSet.getInt("accountnumber");
    		}else {
    			
    			accountNumByCustomerId = 0;
    		}
    		
    		if(accountNumByCustomerId == accountNumber) {
    			
    			isVerity = true;
    		}
    		
    	}catch(ClassNotFoundException|SQLException e) {
    		
    		System.out.println(e.getMessage());
    	}
		
		
		
		return isVerity;

		
	}
	
	
	
	@Override
	public double getAccountBalance(int accountNumber) throws BusinessException{
		double balance = 0.00;
		
        try(Connection connection = PostgresDBConncetion.getConnection()){
    		
    		String sql = BankAccountQueries.GET_ACCOUNT_BALACNE;
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setDouble(1,accountNumber);
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		if(resultSet.next()) {
    			
    			balance = resultSet.getInt("balance");
    		}else {
    			
    			throw new BusinessException("Account Number Doesn't Exit !!!... \n Please Contact Bank Staff... \n"
    					+ "===THANK YOU===");
    		}
    		
    	}catch(ClassNotFoundException|SQLException e) {
    		
    		System.out.println(e.getMessage());
    	}
		
		
		
		return balance;
		
	}
   
	
	@Override
	public CustomerInfo getCustomerDetailByAccountNumber(int customerId) throws BusinessException{
        
		CustomerInfo customerInfo = new CustomerInfo();
		
        try(Connection connection = PostgresDBConncetion.getConnection()){
    		
    		String sql = BankAccountQueries.GET_CUSTOMERINFO_BY_CUSTOMERID;
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1,customerId);
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		if(resultSet.next()) {
    			
    			
    			customerInfo.setCustomerId(customerId);
    			customerInfo.setFirstName(resultSet.getString("firstname"));
    			customerInfo.setLastName(resultSet.getString("lastname"));
    			customerInfo.setAddressStreetName(resultSet.getString("address"));
    			customerInfo.setCity(resultSet.getString("city"));
    			customerInfo.setState(resultSet.getString("state"));
    			customerInfo.setPhoneNumber(resultSet.getString("phonenumber"));
    			customerInfo.setSSN(resultSet.getString("ssn"));
    			customerInfo.setJoinDate(resultSet.getDate("joindate"));
    			customerInfo.setCustomerRegisterId(resultSet.getInt("customer_registerid"));
    			customerInfo.setZipCode(resultSet.getString("zipcode"));
    			
    			
    			
    			
    			
    		   
    		}else {
    			
    			throw new BusinessException("Customer ID doesn't Exist ...\nPlease Try Again ...");
    		}
    		
    	}catch(ClassNotFoundException|SQLException e) {
    		
    		System.out.println(e.getMessage());
    	}
    
        
     return customerInfo;
		
		
		
	}


	@Override
	public BankAccountRegister getAllAccDetailsByAccountNumber(int accountNumber) throws BusinessException {
	
		BankAccountRegister bankAccountRegister = new BankAccountRegister();
		
        try(Connection connection = PostgresDBConncetion.getConnection()){
    		
    		String sql = BankAccountQueries.GET_ACCOUNTINFO_BY_ACC_NUM;
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1,accountNumber);
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		if(resultSet.next()) {
    			
    			
    			bankAccountRegister.setCustomerAccountNum(accountNumber);
    			bankAccountRegister.setBalance(resultSet.getDouble("balance"));
    			bankAccountRegister.setOpeningBalance(resultSet.getDouble("openingbalance"));
    			bankAccountRegister.setAccountName(resultSet.getString("accountname"));
    			bankAccountRegister.setCustomerId(resultSet.getInt("customers_customerid"));
    			bankAccountRegister.setDateOpened(resultSet.getDate("dateopened"));
    			bankAccountRegister.setInterest(resultSet.getDouble("interest"));
    			bankAccountRegister.setAccountStatus(resultSet.getInt("account_status"));
    			bankAccountRegister.setAccountType(resultSet.getInt("accounttype"));
    			bankAccountRegister.setBranchLoc(resultSet.getString("branchloc"));
    			
    			
    			
    		   
    		}else {
    			
    			throw new BusinessException("Account Number doesn't Exist ...\nPlease Try Again ...");
    		}
    		
    	}catch(ClassNotFoundException|SQLException e) {
    		
    		System.out.println(e.getMessage());
    	}
    
        
     return bankAccountRegister;
		
	}
    
	@Override
	public List<BankAccountRegister> getAllAccDetailsListByAccountNumber(int accountNumber) throws BusinessException{
		
		List<BankAccountRegister> allAccountList = new ArrayList<>();
		
		 try(Connection connection = PostgresDBConncetion.getConnection()){
	    		
	    		String sql = BankAccountQueries.GET_ACCOUNTINFO_BY_ACC_NUM;
	    		
	    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    		preparedStatement.setInt(1,accountNumber);
	    		
	    		ResultSet resultSet = preparedStatement.executeQuery();
	    		
	    		while(resultSet.next()) {
	    			
	    			BankAccountRegister bankAccountBalance = new BankAccountRegister(accountNumber, resultSet.getDouble("balance"), 
	    					resultSet.getDouble("openingbalance"), resultSet.getString("accountname"), resultSet.getInt("customers_customerid"),
	    					resultSet.getDate("dateopened"), resultSet.getDouble("interest"), resultSet.getInt("account_status"),
	    					resultSet.getInt("accounttype"), resultSet.getString("branchloc"));
	    			
	    			allAccountList.add(bankAccountBalance);
	    			
	    		}
	    			
	    
	    		
	    	}catch(ClassNotFoundException|SQLException e) {
	    		
	    		throw new BusinessException("Account Number doesn't Exist ...\nPlease Try Again ...");
	    	}
	    
		
		
		return allAccountList;
		
	}
	
	@Override
	public List<BankAccountRegister> getAllAccDetailsListByEmployee() throws BusinessException{
		
		
		 List<BankAccountRegister> allAccountList = new ArrayList<>();
		
		 try(Connection connection = PostgresDBConncetion.getConnection()){
	    		
	    		String sql = BankAccountQueries.GET_ACCOUNTINFO;
	    		
	    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    		
	    		ResultSet resultSet = preparedStatement.executeQuery();
	    		
	    		while(resultSet.next()) {
	    			
	    			BankAccountRegister bankAccountBalance = new BankAccountRegister(resultSet.getInt("accountnumber"), resultSet.getDouble("balance"), 
	    					resultSet.getDouble("openingbalance"), resultSet.getString("accountname"), resultSet.getInt("customers_customerid"),
	    					resultSet.getDate("dateopened"), resultSet.getDouble("interest"), resultSet.getInt("account_status"),
	    					resultSet.getInt("accounttype"), resultSet.getString("branchloc"));
	    			
	    			allAccountList.add(bankAccountBalance);
	    			
	    		}
	    			
	    
	    		
	    	}catch(ClassNotFoundException|SQLException e) {
	    		
	    		throw new BusinessException("Account Number doesn't Exist ...\nPlease Try Again ...");
	    	}
	    
		
		
		return allAccountList;
		
		
		
	}


	@Override
	public List<BankAccountRegister> getAllAccountBalanceByUserId(int customerId) throws BusinessException{
		
		List<BankAccountRegister> accountBalanceList = new ArrayList<>();
		
		 try(Connection connection = PostgresDBConncetion.getConnection()){
	    		
	    		String sql = BankAccountQueries.GET_ACC_BALANCE_BY_CUSTOMERID;
	    		
	    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    		preparedStatement.setInt(1,customerId);
	    		
	    		ResultSet resultSet = preparedStatement.executeQuery();
	    		
	    		while(resultSet.next()) {
	    			
	    			BankAccountRegister bankAccountBalance = new BankAccountRegister(resultSet.getInt("accountnumber"), resultSet.getDouble("balance"), 
	    					resultSet.getDouble("openingbalance"), resultSet.getString("accountname"), customerId,
	    					resultSet.getDate("dateopened"), resultSet.getDouble("interest"), resultSet.getInt("account_status"),
	    					resultSet.getInt("accounttype"), resultSet.getString("branchloc"));
	    			
	    			accountBalanceList.add(bankAccountBalance);
	    			
	    		}
	    			
	    
	    		
	    	}catch(ClassNotFoundException|SQLException e) {
	    		
	    		throw new BusinessException("Account Number doesn't Exist ...\nPlease Try Again ...");
	    	}
	    
		
		
		return accountBalanceList;
		
		
	}
	
	@Override
	public List<BankAccountRegister> getAllAccountByCustomerIdandAccountTYpe(int customerId,int accountType) throws BusinessException{
		
		
		List<BankAccountRegister> accountBalanceList = new ArrayList<>();
		
		 try(Connection connection = PostgresDBConncetion.getConnection()){
	    		
	    		String sql = BankAccountQueries.GET_ALLACC_BY_CUSTOMERID_ACCOUNTTYPE ;
	    		
	    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    		preparedStatement.setInt(1,customerId);
	    		preparedStatement.setInt(2,accountType);
	    		
	    		ResultSet resultSet = preparedStatement.executeQuery();
	    		
	    		while(resultSet.next()) {
	    			
	    			BankAccountRegister bankAccountBalance = new BankAccountRegister(resultSet.getInt("accountnumber"), resultSet.getDouble("balance"), 
	    					resultSet.getDouble("openingbalance"), resultSet.getString("accountname"), customerId,
	    					resultSet.getDate("dateopened"), resultSet.getDouble("interest"), resultSet.getInt("account_status"),accountType,resultSet.getString("branchloc"));
	    			
	    			accountBalanceList.add(bankAccountBalance);
	    			
	    		}
	    			
	    
	    		
	    	}catch(ClassNotFoundException|SQLException e) {
	    		
	    		throw new BusinessException("Account Number doesn't Exist ...\nPlease Try Again ...");
	    	}
	    
		
		
		  return accountBalanceList;
		
		
		
		
	}



	@Override
	public BankAccountRegister updateBankAccount(int accountNumber, double enteredAmount, int transactionType) throws BusinessException {
		
		BankAccountRegister accountDetails = null;
		
		try(Connection connection = PostgresDBConncetion.getConnection()){
    		
			int successTransaction = createTransaction(transactionType, enteredAmount, accountNumber);
			
			if(successTransaction == 1) {
		
			    double updateAmount = getCalculation(accountNumber, transactionType, enteredAmount);
				
			    String updateBankAccountsql = BankAccountQueries.UPDATE_BANKACC_BY_TRANSACTIONTYPE;
			    
			    PreparedStatement preparedSatementBankAccount = connection.prepareStatement(updateBankAccountsql);	
			    preparedSatementBankAccount.setDouble(1,updateAmount);
			    preparedSatementBankAccount.setInt(2,accountNumber );
			    
			    
			    int updateStatus = preparedSatementBankAccount.executeUpdate();
			    
			    if(updateStatus ==1) {
			    	
			    	accountDetails = getAllAccDetailsByAccountNumber(accountNumber);
			    }
				
			}
	    	
    	

		 }catch(ClassNotFoundException|SQLException e) {
	    		
			    logger.trace(e.getMessage());
	    		throw new BusinessException("Account Number doesn't Exist ...\nPlease Try Again ...");
	    }	
		return accountDetails;
	}
	
	
	@Override
	public void updateBankAccOnly(int accountNumber, double enteredAmount, int transactionType) throws BusinessException {
	
		
		try(Connection connection = PostgresDBConncetion.getConnection()){
    		
			int successTransaction = createTransaction(transactionType, enteredAmount, accountNumber);
			
			if(successTransaction == 1) {
		
			    double updateAmount = getCalculation(accountNumber, transactionType, enteredAmount);
				
			    String updateBankAccountsql = BankAccountQueries.UPDATE_BANKACC_BY_TRANSACTIONTYPE;
			    
			    PreparedStatement preparedSatementBankAccount = connection.prepareStatement(updateBankAccountsql);	
			    preparedSatementBankAccount.setDouble(1,updateAmount);
			    preparedSatementBankAccount.setInt(2,accountNumber );
			    
			    
			    int updateStatus = preparedSatementBankAccount.executeUpdate();
			    
			    if(updateStatus !=1) {
			    	throw new BusinessException("Update Bank Account transaction fail...");
			    	

			    }
				
			}
	    	
    	

		 }catch(ClassNotFoundException|SQLException e) {
	    		
			    logger.trace(e.getMessage());
	    		throw new BusinessException("Account Number doesn't Exist ...\nPlease Try Again ...");
	    }	
		
	}

	


	@Override
	public int createTransaction(int transactionType ,double amount,int accountNumber) throws BusinessException {
		
		int success = 0;
		
		
		try(Connection connection = PostgresDBConncetion.getConnection()){
    		
	    	
	    		/*query for bank_account table */
	    		String insertBankAccount = BankAccountQueries.INSERT_BANK_TRANSACTION;
	    		
	    		PreparedStatement preparedSatementBankAccount = connection.prepareStatement(insertBankAccount);
	    		
	    		preparedSatementBankAccount.setInt(1,transactionType );
	    		
	    		/* Check negative Amount*/
	    		if(amount > 0) {
	    		   preparedSatementBankAccount.setDouble(2,amount );
	    		}else {
	    			throw new BusinessException("Negetive withdraw amount isn't allowed ... ");
	    		}
	    		
	    		preparedSatementBankAccount.setInt(3,accountNumber);
	    	
	    	   
	    	   
	    	    double updateBalanceAmount = getCalculation(accountNumber, transactionType, amount);
	    	
	    	    if(updateBalanceAmount > 0 ) {
	    	    	preparedSatementBankAccount.setDouble(4, updateBalanceAmount);
	    	    }else {
	    	    	 throw new BusinessException("More than your account balance isn't allowed ...\nPlease try again ...");
	    	    }
	    		
	    		
	    		
	    		
	    	    preparedSatementBankAccount.setDate(5, new Date(new java.util.Date().getTime()));
	    		
	    		
	    		
	    		int statusInsertTranaction =  preparedSatementBankAccount.executeUpdate();
	    		
	    		if(statusInsertTranaction == 1) {
	    			success =1;
	    		}else {
	    			success =0;
	    		}
    	
		 }catch(ClassNotFoundException|SQLException e) {
	    		
	    		throw new BusinessException("Account Number doesn't Exist ...\nPlease Try Again ...");
	    }	
    
    	return success;
    	
    	
     }
	
	
	
	
	
	public double getCalculation(int accountNumber , int transactionType, double amount ) throws BusinessException{
		double updateBalanceAmount = 0.00;
		
		
		try(Connection connection = PostgresDBConncetion.getConnection()){
			
			
			double balanceAmount = getAccountBalance(accountNumber);
			
			/* Transaction Type
			 * 1 - Withdraw
			 * 2 - Deposit
			 */
			
		    if(transactionType == 1) {
    	    	updateBalanceAmount = balanceAmount - amount;
    	    }else if(transactionType == 2) {
    	    	updateBalanceAmount = balanceAmount + amount;
    	    }
		    
		    
		    
		    if(updateBalanceAmount < 0) {
		    	
		    	throw new BusinessException(" More than Account balance isn't allowed ... \nSORRY !! Please try Again");
		    }
			
			
		}catch(ClassNotFoundException|SQLException e) {
    		
    		throw new BusinessException("Account Number doesn't Exist ...\nPlease Try Again ...");
       }	
		
		
		
		return updateBalanceAmount;
		
	}



	@Override
	public BankAccountRegister transferAmount(int sendingAccountNumber, int receivingAccountNumber, double transferAmount)
			throws BusinessException {
		 BankAccountRegister bankAccountDetails = null ;
		
        try(Connection connection = PostgresDBConncetion.getConnection()){
			
			
			double sendingbalanceAmount = getAccountBalance(sendingAccountNumber);
			
			if((sendingbalanceAmount-transferAmount) > 0 ) {
				//updating sending Account Balance
				// 1 - means making a withdraw transaction
				bankAccountDetails = updateBankAccount(sendingAccountNumber, transferAmount, 1);
				
			}else {
				
				throw new BusinessException("You can't transfer more than your balance amount ...\nPlease try another amount ...");
			}

			double receivingbalanceAmount = getAccountBalance(receivingAccountNumber);
			
			double newupdatebalanceAmount = receivingbalanceAmount + transferAmount;
		    
			
			if(newupdatebalanceAmount > 0) {
				
				//updating receiving account 
				// 2 - means a making deposit transaction
				
				updateBankAccOnly(receivingAccountNumber, transferAmount, 2);
				
			}
		    
		    
		   
		
			
		 }catch(ClassNotFoundException|SQLException e) {
    		
    		throw new BusinessException("Account Number doesn't Exist ...\nPlease Try Again ...");
        }	
		
		
		
		return bankAccountDetails;
	 }



	@Override
	public List<AccountTransaction> getAllTransactionsByAccountNumber(int accountNumber) throws BusinessException {
		
		List<AccountTransaction> accountTransactionList = new ArrayList<>();
		
		 try(Connection connection = PostgresDBConncetion.getConnection()){
	    		
	    		String sql = BankAccountQueries.GET_ALL_TRANSACTION_BY_ACCNUM ;
	    		
	    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    		preparedStatement.setInt(1,accountNumber);
	    		
	    		
	    		ResultSet resultSet = preparedStatement.executeQuery();
	    		
	    		while(resultSet.next()) {
	    			
	    			AccountTransaction accountTransaction = new AccountTransaction(resultSet.getInt("transaction_id"),resultSet.getInt("trans_type") , resultSet.getDouble("amount"), 
	    					accountNumber, resultSet.getDouble("total_balance"), resultSet.getDate("transactiondate"));
	    			
	    			accountTransactionList.add(accountTransaction);
	    			
	    		}
	    			
	    
	    		
	    	}catch(ClassNotFoundException|SQLException e) {
	    		
	    		throw new BusinessException("Account Number doesn't Exist ...\nPlease Try Again ...");
	    	}
	    
		
		
		return accountTransactionList;
	}



	@Override
	public int updateBankAccountStatus(int accountNumber, int accountStatus) throws BusinessException {
		int status = 0;
		
        try(Connection connection = PostgresDBConncetion.getConnection()){
    		
	
			    String updateBankAccountsql = BankAccountQueries.UPDATE_BANKACCSTATUS_BY_ACCNUM;
			    
			    PreparedStatement preparedSatementBankAccount = connection.prepareStatement(updateBankAccountsql);	
			    preparedSatementBankAccount.setDouble(1,accountStatus);
			    preparedSatementBankAccount.setInt(2,accountNumber );
			    
			    
			    int updateStatus = preparedSatementBankAccount.executeUpdate();
			    
			    if(updateStatus !=1) {
			    	throw new BusinessException("Update Bank Account transaction fail...");
			    	

			    }
			    
			    status = 1;
			
	    	
    	

		 }catch(ClassNotFoundException|SQLException e) {
	    		
			    logger.trace(e.getMessage());
	    		throw new BusinessException("Account Number doesn't Exist ...\nPlease Try Again ...");
	    }	
		
		
		return status;
	}



	@Override
	public int createEmployeeUserProfile(String username, String password, String email, int usertype)
			throws BusinessException {
		int status =0;
		
		
		try(Connection connection = PostgresDBConncetion.getConnection()){
    		
			
		    String updateBankAccountsql = BankAccountQueries.REGISTER_EMPLOYEE_USER;
		    
		    PreparedStatement preparedSatementBankAccount = connection.prepareStatement(updateBankAccountsql);	
		    preparedSatementBankAccount.setString(1,username.toLowerCase());
		    preparedSatementBankAccount.setString(2,password);
		    preparedSatementBankAccount.setString(3,email);
		    preparedSatementBankAccount.setInt(4,usertype);
		    
		    
		    int updateStatus = preparedSatementBankAccount.executeUpdate();
		    
		    if(updateStatus !=1) {
		    	throw new BusinessException("Database Query Failed ...");
		    
		    }
		    
		    status = 1;
		
    	
	

	    }catch(ClassNotFoundException|SQLException e) {
    		
		    logger.trace(e.getMessage());
		    throw new BusinessException("Username or Email is already exist...\nplease try again or contact admin...");
		    
       }	
	
		

		return status;
	}
		
		
}		
		
