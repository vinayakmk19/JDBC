package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionConcurrencyApp {

	public static void main(String[] args) throws SQLException {

		
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "root123");
		System.out.println("Isolation Level before modification is  :: "+connection.getTransactionIsolation());
		connection.setTransactionIsolation(4);
		System.out.println("Isolation level after modification is :: "+connection.getTransactionIsolation());
		
		
	}

}
