package com.abc.mainapp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class StoredProcedureApplication {

	private static final String GET_PRODUCT_BY_NAME = "{call GET_PRODUCT_BY_NAME(?,?,?)}";

	public static void main(String[] args) {

		// Resource used in the application
		Connection connection = null;
		CallableStatement cst = null;
		Scanner scanner = null;

		// local variable used in the application
		int id = 0;

		// DB credentials info
		String url = "jdbc:mysql:///abc";
		String username = "root";
		String password = "root123";

		// Step1.Establish the connection b/w java app and database
		try {

			scanner = new Scanner(System.in);
			if (scanner != null) {
				System.out.print("Enter the id:: ");
				id = scanner.nextInt();
			}

			connection = DriverManager.getConnection(url, username, password);

			if (connection != null) {

				cst = connection.prepareCall(GET_PRODUCT_BY_NAME);

				if (cst != null) {

					// Setting the input parameters
					cst.setInt(1, id);

					// Setting the output parameters
					cst.registerOutParameter(2, Types.INTEGER);
					cst.registerOutParameter(3, Types.VARCHAR);

					// Execute the StoredProcedure by making a call to DBE
					cst.execute();

					// Retrieving based of getXXX(int position)
					System.out.println("The product cost is :: " + cst.getInt(2));
					System.out.println("The product name is :: " + cst.getString(3));

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
