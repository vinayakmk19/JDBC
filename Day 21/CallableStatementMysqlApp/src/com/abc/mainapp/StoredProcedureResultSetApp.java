package com.abc.mainapp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StoredProcedureResultSetApp {

	private static final String GET_DETAILS_BY_NAME = "{call GET_DETAILS_BY_NAME(?,?)}";

	public static void main(String[] args) {

		// Resource used in the application
		Connection connection = null;
		CallableStatement cst = null;
		ResultSet resultSet = null;
		Scanner scanner = null;

		// local variable used in the application
		String pname1 = null;
		String pname2 = null;

		// DB credentials info
		String url = "jdbc:mysql:///abc";
		String username = "root";
		String password = "root123";

		// Step1.Establish the connection b/w java app and database
		try {

			scanner = new Scanner(System.in);
			if (scanner != null) {
				System.out.print("Enter the first product name:: ");
				pname1 = scanner.next();

				System.out.print("Enter the second product name:: ");
				pname2 = scanner.next();

			}

			connection = DriverManager.getConnection(url, username, password);

			if (connection != null) {

				cst = connection.prepareCall(GET_DETAILS_BY_NAME);

				if (cst != null) {

					// Setting the input parameters
					cst.setString(1, pname1);
					cst.setString(2, pname2);

					// Execute the StoredProcedure by making a call to DBE
					cst.execute();

					// Retrieving the resultSet object

					resultSet = cst.getResultSet();

					if (resultSet != null) {

						while (resultSet.next()) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("PID is :: " + resultSet.getInt(1));
							System.out.println("PNAME is ::" + resultSet.getString(2));
							System.out.println("PCOST is :: " + resultSet.getInt(3));
							System.out.println("Quantity is :: " + resultSet.getInt(4));
							System.out.println("Availability is :: " + resultSet.getInt(5));
							System.out.println("----------------------------------------------------------");

						}

					}

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// closing scanner stream
			if (scanner != null) {
				scanner.close();
			}

			// Closing jdbc objects

			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (cst != null) {
					cst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
