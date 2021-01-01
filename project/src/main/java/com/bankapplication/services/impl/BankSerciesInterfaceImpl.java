package com.bankapplication.services.impl;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankapplication.dao.BankServicesDAOInterface;
import com.bankapplication.dao.impl.BankServicesDAOImpl;
import com.bankapplication.exception.BusinessException;
import com.bankapplication.model.LogInModel;
import com.bankapplication.model.account.AccountTransaction;
import com.bankapplication.model.account.BankAccountRegister;
import com.bankapplication.model.customer.BalanceTranfer;
import com.bankapplication.model.customer.CustomerInfo;
import com.bankapplication.model.customer.CustomerViewAccBalance;
import com.bankapplication.services.BankServicesInterface;

public class BankSerciesInterfaceImpl implements BankServicesInterface{
	
	
	//log4j
    private static Logger logger = Logger.getLogger(BankSerciesInterfaceImpl.class);
	
    
	BankServicesDAOInterface bankServiceDAO = new BankServicesDAOImpl();
	
   //Scanner
	
	Scanner scanner = new Scanner(System.in);
	
	
   @Override	
   public int registerUsername(String username, String password, String email) throws BusinessException {
	   
	   int c =0;
	   
	 
	   if(username != null && password !=null && email != null) {
		   
		   
		   //username and password should be more than 3 characters
		   if(username.length() > 3 && password.length() > 3) {
			   
			   c = bankServiceDAO.registerUsername(username, password, email);
			   
		   }else {
			   
			   System.out.println();
			   System.out.println("Username and Password should be more than 3 character .... Please Type Again");
		   }
	        	
	     
	   }else {
		   
		   System.out.println();
		   throw new BusinessException("Please Check username, password ... Try Again ...");
	   }
	  
	   
	   
        return c;
		
	}
   
   
   @Override
   public boolean isVerifyUsernamePassword(String username,String password) throws BusinessException{
	   
	   boolean isValide = false;
	   
	   if(username != null && password !=null) {
		   
             if(username.length() > 3 && password.length() > 3) {
			   
            	 if(bankServiceDAO.getUserRegisterDetail(username, password).size() != 0) {
            		 isValide = true;
            	 }
			   
		     }else {
			   
			   System.out.println();
			   throw new BusinessException("Username and Password don't exist... Sorry ... Please Try Again !!!");
		     }
		   
	   }else{
		   
		   throw new BusinessException("Username and Password don't exist... Sorry ... Please Try Again !!!");
		   
	   }
	   
	   
	   
	   return isValide;
   }
   
   
   public int getUserType(String username) throws BusinessException{
	   
	   /* 1 for Bank Customers
	    * 2 for Bank Employees
	    * 3 for Admin
	    */
	   
	   
	   
	   int isCusEmp = 0;
	   
	   if(username != null ) {
		   
           if(username.length() > 3) {
			   
        	   isCusEmp = bankServiceDAO.getUserType(username);
			   
		     }else {
			   
			   System.out.println();
			   throw new BusinessException("Username and Password don't exist... Sorry ... Please Try Again !!!");
		     }
		   
	   }else{
		   
		   throw new BusinessException("Username and Password don't exist... Sorry ... Please Try Again !!!");
		   
	   }
	   
	   
	   return isCusEmp ;
   }


/* This is implementation part of New Bank Account opening Services */   
   
@Override
public boolean applyNewBankAcoount(CustomerInfo customerinfo, BankAccountRegister bankAccountRegister) throws BusinessException {
	boolean isSuccess = false;
	
	
	if(bankAccountRegister != null) {
		
		if(bankAccountRegister.getAccountType() != 3) {
			
			
		
	          // System.out.println(" You are now DAO Interface :");
	           int userId = bankServiceDAO.getUserId(LogInModel.getUsername());
	           int customerId = bankServiceDAO.getCustomerId(userId);
	           
	           if(customerId != 0) {
	        	    
			        
			         int BankStatus =  bankServiceDAO.createBankAccount(bankAccountRegister,customerId);
					 if(BankStatus == 1) {
					        isSuccess = true;
					 }else {
					       isSuccess = false;
					 }
			       
	              
	        	    	 
	        	 	
	           
	           }else {
	        	   
	        	   if(customerinfo != null) {
	        	   
			        	   if(verifyBankAccountRequirements(customerinfo) && verifyBankAccountRequirements(bankAccountRegister)) {
				        	   int customerStatus = bankServiceDAO.createCustomerProfile(customerinfo,userId);
				        	   int BankStatus = 0;
				        	   if(customerStatus == 1) {
					        	   int customerIdAgain = bankServiceDAO.getCustomerId(userId);
					        	   BankStatus =  bankServiceDAO.createBankAccount(bankAccountRegister,customerIdAgain);
				        	   }
				               if(BankStatus == 1 && customerStatus == 1 ) {
			                       isSuccess = true;
			                    }else {
			                	 isSuccess = false;
			                    }
			        	   }
	        	   }else {
	        		   
	        		   throw new BusinessException(" Account Requirements should be fullfil !! ");
	        		   
	        	   }
	        	
	           }
	         
	         
		} else {
			
			throw new BusinessException(" Account Requirements should be fullfil  ");
		}
	
	} else {
		
		throw new BusinessException("Some Data are missing.... Please try Again...Or.. Please contact closest Brank Location. \n ***THANK YOU***");
		
	}
	
	
	return isSuccess;
}


@Override
public void viewAccountBalance(CustomerViewAccBalance customerViewAccBalance , int choice) throws BusinessException {
	
	if(customerViewAccBalance != null) {
		
		int accountNumber = customerViewAccBalance.getAccountNumber();
		
		if(choice == 1) {
			
            int customeridByUsername = bankServiceDAO.getCustomerId(bankServiceDAO.getUserId(LogInModel.getUsername()));
			
			
			if(bankServiceDAO.isVerifyAccountNumByCustomerId(accountNumber, customeridByUsername)) {
				
			    BankAccountRegister bankBalance = bankServiceDAO.getAllAccDetailsByAccountNumber(accountNumber);
			    System.out.println();
			    logger.fatal("Customer Name : " + LogInModel.getUsername().toUpperCase());
			    logger.fatal("-------------------------");
				bankBalance.displayAccountDeatails();
			}else {	
				
				throw new BusinessException("Entered account number doesn't match with your account !!... ");
			}
			
	
		
			
		}else if(choice == 2) {
			
			//int customerId = bankServiceDAO.getCustomerId(bankServiceDAO.getUserId(LogInModel.getUsername()));
			List<BankAccountRegister> accountBalanceList = bankServiceDAO.getAllAccountBalanceByUserId(customerViewAccBalance.getByCustomerId());
			if(accountBalanceList.size() != 0) {
				System.out.println();
				logger.fatal("Customer Name : " + LogInModel.getUsername().toUpperCase());
				logger.fatal("-------------------------");
				int n =0;
				for(BankAccountRegister list: accountBalanceList ) {
					list.displayAccountDeatails();
					n++;
				}
				System.out.println();
				logger.fatal("YOU HAVE " + n + " ACCOUNTS ... " );
			}else {
				
				throw new BusinessException(" No Record Found ....");
			}
			
		}else {
			throw new BusinessException("Enter right choice .... \nTry Again ...");
		}
	}else {
		
		throw new BusinessException("Account Number doesn't match ....\nTry Again...");
	}

	
}


@Override
public int bankTransaction(int accountTypeChoice, int tranastionType) throws BusinessException{
	
	int success = 0;
	
	/* This is Withdraw Bank Services
	 * 1 - for withdraw
	 * 2 - for deposit
	 */
	if(accountTypeChoice > 0) {
		
		int customerId = bankServiceDAO.getCustomerId(bankServiceDAO.getUserId(LogInModel.getUsername()));
		
		List<BankAccountRegister> accountBalanceList = bankServiceDAO.getAllAccountByCustomerIdandAccountTYpe(customerId, accountTypeChoice);
		if(accountBalanceList.size() > 1) {
			System.out.println();
			logger.fatal("You have more than one account");
			logger.fatal("------------------------------");
			for(BankAccountRegister list: accountBalanceList ) {
				list.displayAccountDeatails();
			}
	        System.out.println();
		    logger.fatal("It seems more than one account...\nPlease enter account number :");
		    int accountNumber = Integer.parseInt(scanner.nextLine());
		    
		    /* test entered account Number match with given customer id or Not */
		    boolean isVarifyAccNum = false;
		    for(BankAccountRegister list: accountBalanceList ) {
		    	if(list.getCustomerAccountNum() == accountNumber ) {
		    		isVarifyAccNum = true;
		    		break;
		    	}
		    	
		    	isVarifyAccNum = false;
			}
		    
		    if(isVarifyAccNum == false) {
		    	
		        throw new BusinessException("Entered account number doesn't match ... \nPlease try again ...");
		 
		    }
		    
		    logger.trace("entetedACCNum: "+ accountNumber );
		    
		    logger.fatal("Please enter Amount what you want:");
		    Double withdramAmount = Double.parseDouble(scanner.nextLine());
		    logger.trace("entetedACCNum: "+ withdramAmount );
		    
		    
		    BankAccountRegister bankAccountDetail = bankServiceDAO.updateBankAccount(accountNumber, withdramAmount, tranastionType);
		    
		    System.out.println();
		    logger.fatal("New " + bankAccountDetail.getAccountName() + " Balance : $" + bankAccountDetail.getBalance()  );
		  
		    
		}else if(accountBalanceList.size() == 1) {
			
			System.out.println("Balance : $" + accountBalanceList.get(0).getBalance());
			int accountNumber = accountBalanceList.get(0).getCustomerAccountNum();
		    logger.trace("entetedACCNum: "+ accountNumber );
			logger.fatal("\nPlease enter Amount what you want to make a withdraw :");
		    Double withdramAmount = Double.parseDouble(scanner.nextLine());
		    logger.trace("entetedACCNum: "+ withdramAmount );
		    
		    
            BankAccountRegister bankAccountDetail = bankServiceDAO.updateBankAccount(accountNumber, withdramAmount, tranastionType);
		    
            logger.fatal("New " + bankAccountDetail.getAccountName() + " Balance : $" + bankAccountDetail.getBalance()  );
		    
		    
			
		}else {
			
			throw new BusinessException("Account haven't found ...\nPlease create bank account with minimum balance...\nPlease try again...");
			
		}
		
		
	     success = 1;
		
	}else {
		
		throw new BusinessException("Account Type doesn't match ... \nPlease try again...");
	}
		

	return success;
}




@Override
public int balanceTranfer(BalanceTranfer balancetransfer) throws BusinessException{
	int success = 0;
	
	if(balancetransfer != null) {
		
		
		
		
	 success = 1;	
	}else {
		
		throw new BusinessException("Tranfer transaction is fail...\nPlease verify all requirements ...\nPlease try again...");
	}
	
	
	return success;
}


@Override
public boolean verifyBankAccountRequirements(CustomerInfo customerinfo) throws BusinessException {
	
	  boolean isvarify =false;
	  
	  
	  if(customerinfo != null) {
			  /*varify phone Number */
			  try {
					  if(customerinfo.getPhoneNumber().matches("[0-9]{10}")) {
						  isvarify =true;
					  }else {
						   logger.fatal("Please enter phone Number in 10 digit only e.g 7734027577" );
						   logger.trace("phoneNumber" + customerinfo.getPhoneNumber());
						   isvarify =false;
					  }
					  
					  /* state
					   * abbreviations e.g IL  */ 
					  
					  if(customerinfo.getState().length() == 2) {
						  isvarify =true;
						  
					  }else {
						  
						  logger.fatal("State should be 2 Upper Char e.g IL ");
						  logger.trace("State:" + customerinfo.getState() );
						  isvarify =false;
					  }
					  
					  
					  /* 5 digit number */
					  
					  if(customerinfo.getZipCode().length() == 5) {
						  isvarify =true;
						  
					  }else {
						  
						  logger.fatal("ZipCode should be 6 digit Number e.g 60660 ");
						  logger.trace("ZibCode:" + customerinfo.getZipCode() );
						  isvarify =false;
					  }
					  
					  /*9 digit Number */
					  
					  if(customerinfo.getSSN().matches("[0-9]{9}")) {
						  isvarify =true;
						  
					  }else {
						  
						  logger.fatal("SSN should be 9 digit Numbers e.g 367780591 ");
						  logger.trace("ZibCode:" + customerinfo.getSSN() );
						  isvarify =false;
					  }
					  
			  }catch(NullPointerException e) {
				  
                   logger.fatal(e.getMessage());
			  }
			  
	  }else {
		  
		  throw new BusinessException("Account requirements doen't match with data...\"nPlease try again..");
	  }
		  
	 
	  
	  return isvarify;
}


@Override
public boolean verifyBankAccountRequirements(BankAccountRegister bankAccountRegister) throws BusinessException {
	  boolean isvarify =false;
	  
	  
	  /* Amount is not allowed in negative
	   * 
	   */
	  if(bankAccountRegister.getBalance() > 0 && bankAccountRegister.getOpeningBalance() > 0) {
		  
		  isvarify =true;
	  }else {
		  
		  logger.fatal("Negetive Amount isn't allowed ... ");
		  logger.trace("Enter Balance Amount" + bankAccountRegister.getBalance());
		  logger.trace("Enter Balance Amount" + bankAccountRegister.getOpeningBalance());
		  isvarify =false;
	  }
	  
	  /* Account status  should be 1 or 2 or 3 
	   * 1 - Active
	   * 2 - Suspend
	   * 3 - Closed 
	   * */
	
	  
	  if(bankAccountRegister.getAccountStatus() == 1 || bankAccountRegister.getAccountStatus() == 2 
			  || bankAccountRegister.getAccountStatus() == 3  ) {
		  isvarify =true;
	  }else {
		  
		  logger.fatal("Account should be any status");
		  logger.trace("AccountStatus :" + bankAccountRegister.getAccountStatus());
		  isvarify =false;
	  }
	  
	  /* Account type  should be 1 or 2  
	   * 1 - Checking Account
	   * 2 - Saving Account 
	   * */
	
	  
	  if(bankAccountRegister.getAccountType() == 1 || bankAccountRegister.getAccountType()  == 2 ) {
		  isvarify =true;
	  }else {
		  
		  logger.fatal("Account Type should be any Checking or Saving ");
		  logger.trace("Account Type :" + bankAccountRegister.getAccountType());
		  isvarify =false;
	  }
	  
	  
	  return isvarify;
}


@Override
public void getAllAccountInfoByEmployee(int byAccViewChoice, int accountNumber) throws BusinessException {
	
   
	if(byAccViewChoice == 1) {
		// view account detail by account number
		  int customerId = bankServiceDAO.getCustomerIdByAccountNumber(accountNumber);
		  
		  CustomerInfo customerInfo = bankServiceDAO.getCustomerDetailByAccountNumber(customerId);

		
		  BankAccountRegister bankAccount =  bankServiceDAO.getAllAccDetailsByAccountNumber(accountNumber);
		 
		  System.out.println();
		  System.out.println("Account Details");
		  System.out.println("-----------------");
		  System.out.println(customerInfo);
		  System.out.println(bankAccount);
		
		
		
	}else if(byAccViewChoice == 2) {
		
		// view all account details
		
		  List<BankAccountRegister> allAccountDetailList = bankServiceDAO.getAllAccDetailsListByEmployee();
		  System.out.println();
		  logger.fatal("All Accounts Detail List");
		  logger.fatal("-------------------------");
		  //System.out.println(customerInfo);
		  
		  if(allAccountDetailList.size() > 0) {
			  for( BankAccountRegister accList : allAccountDetailList ) {
				  logger.fatal(accList);
			  }
		  }else {
			  
			  throw new BusinessException("No Record Found...");
		  }
		  System.out.println();
		
	}else {
		
		throw new BusinessException("SORRY!!...\nYou haven't choosen any choice... ");
	}
	
	
	
}


@Override
public void getAllTransactionByAccountNumber(int accountNumber) throws BusinessException {
	
	if(accountNumber > 0) {
		
		
		List<AccountTransaction>  accountTransactionList = bankServiceDAO.getAllTransactionsByAccountNumber(accountNumber);
		
		logger.fatal("All transaction of account number : " + accountNumber);
		logger.fatal("------------------------------------");
		
		for(AccountTransaction accountTransaction : accountTransactionList ) {
			
			logger.fatal(accountTransaction.toString());
			
		}
		
		
	}else {
		
		throw new BusinessException("Account Number doesn't exist !!...\nPlease try again ....");
	}
	
}


@Override
public int changeBankAccountStatus(int accountNumber) throws BusinessException {
	int status = 0;
	if(accountNumber > 0) {
		System.out.println();
		logger.fatal(" ACCOUNT STATUS NUMBER");
		logger.fatal("------------------------");
		logger.fatal("1) ACTIVE");
		logger.fatal("2) SUSPEND");
		logger.fatal("3) CLOSED");
		logger.fatal("------------------------");
		logger.fatal("Please enter number to change status:" );
		int accountStatus = Integer.parseInt(scanner.nextLine());
		
		status =  bankServiceDAO.updateBankAccountStatus(accountNumber, accountStatus);
		
		if(status == 1) {
			
			logger.fatal("BAnk Account status changed successfully ...");
		}
		
	}else {
		
		throw new BusinessException("Account Number doesn't exist...\nPlease try again...");
	}
	
	
	
	return status;
}


@Override
public int createEmployeeUserProfile(String username, String password, String email, int usertype)
		throws BusinessException {
     int createStatus = 0;
     
     if(username.length() !=0 && password.length() !=0 && usertype == 2) {
    	 
    	 createStatus =  bankServiceDAO.createEmployeeUserProfile(username, password, email, usertype);
    	 
    	 if(createStatus ==0) {
    		 throw new BusinessException("Action is faill...\nPlease try again...");
    	 }
     }

	return createStatus;
}






}
