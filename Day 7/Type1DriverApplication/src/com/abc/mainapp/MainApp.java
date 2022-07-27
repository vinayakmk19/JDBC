package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainApp {

	public static void main(String[] args) {

		// Organize the resource for our code
		Connection connection = null;
		String url = "jdbc:odbc:abc";
		String user = "system";
		String password = "root";

		try {
			// Step1:Loading and registering driver in java application.

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println("Driver loaded succesfully!!!!");

			// Step2: Establishing the connection with the database
			connection = DriverManager.getConnection(url, user, password);

			if (connection != null) {
				System.out.println("Connection established succesfully");
			}

		} catch (ClassNotFoundException e) {

			System.out.println("Exception occured due to :: " + e.getMessage());

		} catch (SQLException e) {
			System.out.println("Exception occured due to :: " + e.getMessage());
		} finally {

			// Closing the resources
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("Exception occured due to :: " + e.getMessage());
			}
		}

	}

}
