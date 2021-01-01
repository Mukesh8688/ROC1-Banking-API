package com.bankapplication.dao.dbutil;

public class BankAccountQueries {
	
	public static String REGISTER_USER = "INSERT INTO project_schema.bankApp_register\n"
			+ "(username,password, email,usertype)\n"
			+ "VALUES(?, ?, ?,?);" ;
	
	public static String REGISTER_EMPLOYEE_USER = "INSERT INTO project_schema.bankApp_register(username,password, email,usertype)\n"
			+ "VALUES(?, ?, ?,?);" ;
	
	
	public static String GET_ALL_REGISTERS_BY_USERNAMEPASSWORD = "SELECT id,username,password,email,usertype from project_schema.bankApp_register\n"
			     + "WHERE username = ? and password = ?";
	
	
	public static String GET_USERTYPE_USERID_BY_USERNAME = "SELECT id , usertype from project_schema.bankApp_register\n"
		     + "WHERE username = ? ; ";
	
	public static String GET_ALL_REGISTERS  = "SELECT username, email from project_schema.bankApp_register ;" ;
	
	
	public static String GET_ALL_REGISTERS_BY_USERNAME  = "SELECT id,username,password,email,usertype from project_schema.bankApp_register\n"
			+ "WHERE username = ? ;" ;
	
	
	public static String GET_CUSTOMERID_BY_USERID = "SELECT customerid\n"
			+ "FROM project_schema.bank_customers WHERE customer_registerid = ? ;";
	
	
	
	public static String INSERT_BANK_ACCOUNT = "INSERT INTO project_schema.bank_account\n"
			+ "(balance, openingbalance, accountname, customers_customerid, dateopened, interest, account_status, accounttype, branchloc)\n"
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);" ;
	
	
	public static String INSERT_BANK_CUSTOMER = "INSERT INTO project_schema.bank_customers\n"
			+ "(firstname, lastname, address, city, state, phonenumber, ssn, joindate, customer_registerid, zipcode)\n"
			+ "VALUES(?,?,?,?,?,?,?,?,?,?);";
			
	
	
	public static String INSERT_BANK_TRANSACTION = "INSERT INTO project_schema.bank_transactions\n"
			+ "(trans_type, amount, account_accountnumber, total_balance, transactiondate)\n"
			+ "VALUES(?, ?, ?, ?,?);";
	
	
	public static String GET_ACCOUNT_BALACNE = "SELECT balance FROM project_schema.bank_account WHERE accountnumber = ? ;";
	
	
	public static String GET_CUSTOMERINFO_BY_CUSTOMERID ="SELECT firstname, lastname, address, city, state, phonenumber, ssn, joindate, customer_registerid, zipcode\n"
			+ "FROM project_schema.bank_customers WHERE customerid = ? ; ";
			
	public static String GET_ACCOUNTINFO_BY_ACC_NUM = "SELECT balance, openingbalance, accountname, customers_customerid, dateopened, interest, account_status, accounttype, branchloc\n"
			+ "FROM project_schema.bank_account\n"
			+ "WHERE accountnumber = ? ; ";
	
	
	public static String GET_ACCOUNTINFO = "SELECT accountnumber,balance, openingbalance, accountname, customers_customerid, dateopened, interest, account_status, accounttype, branchloc\n"
			+ "FROM project_schema.bank_account ; ";

	
	public static String GET_ACC_BALANCE_BY_CUSTOMERID = "SELECT accountnumber,balance, openingbalance, accountname, dateopened, interest, account_status, accounttype, branchloc\n"
			+"FROM project_schema.bank_account\n"
			+ "WHERE customers_customerid  = ? ;";
	
	public static String GET_ACC_NUMBER_BY_CUSTOMERID = "SELECT accountnumber,balance, openingbalance, accountname, dateopened, interest, account_status, accounttype, branchloc\n"
			+"FROM project_schema.bank_account\n"
			+ "WHERE customers_customerid  = ? ;";
	
	
	public static String GET_ALLACC_BY_CUSTOMERID_ACCOUNTTYPE = "SELECT accountnumber,balance, openingbalance, accountname, customers_customerid, dateopened, interest, account_status, branchloc\n"
			+ "FROM project_schema.bank_account\n"
			+ "WHERE customers_customerid  = ? and accounttype = ? ; ";
	
	
	public static String UPDATE_BANKACC_BY_TRANSACTIONTYPE = "UPDATE project_schema.bank_account\n"
			+ "SET balance= ? WHERE accountnumber= ? ;";
	
	public static String GET_ALL_TRANSACTION_BY_ACCNUM ="SELECT transaction_id, trans_type, amount, total_balance, transactiondate\n"
			+ "FROM project_schema.bank_transactions WHERE account_accountnumber = ? ;";
	
	public static String UPDATE_BANKACCSTATUS_BY_ACCNUM = "UPDATE project_schema.bank_account\n"
			+ "SET account_status= ? WHERE accountnumber= ? ;";
	


}
