package com.bankapplication.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDBConncetion {
	
	
	private static Connection connection;
	
	
	// making private constructor not allow to initialize object of this class
	private PostgresDBConncetion(){
		
	}
	
	
	
	
	public static Connection getConnection() throws ClassNotFoundException,SQLException{
		
		// Register Driver
		Class.forName(DbUtilProperties.DRIVER);
		
		
		//Using getConnection method of DriverManager Class
		// establish conncetion with PostgresSQL Database
		connection = DriverManager.getConnection(DbUtilProperties.URL, DbUtilProperties.USERNAME, DbUtilProperties.PASSWORD);
		//System.out.println("Connection Successfully Done!!");
		
		return connection;
	}

}
