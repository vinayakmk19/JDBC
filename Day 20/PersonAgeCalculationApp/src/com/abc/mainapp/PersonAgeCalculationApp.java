package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCalculationApp {

	// private static final String SQLSELECTQUERY = "select
	// datediff(now(),dob)/365.25 as age from employee where id=?";
	private static final String SQLSELECTQUERY = "select from_days(datediff(now(),dob))as age from employee where id=?";

	public static void main(String[] args) {

		// Resource used in the application
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Scanner scanner = null;

		// local variable used in the application
		int id = 0;

		// DB credentials info
		String url = "jdbc:mysql:///abc";
		String username = "root";
		String password = "root123";

		try {

			scanner = new Scanner(System.in);

			if (scanner != null) {

				// Taking the inputs from the user to execute the query

				System.out.print("Enter the id of the student :: ");
				id = scanner.nextInt();

			}

			// Step1.Establish the connection b/w java app and database
			connection = DriverManager.getConnection(url, username, password);

			if (connection != null) {

				// Step2.Create the PreparedStatement object
				preparedStatement = connection.prepareStatement(SQLSELECTQUERY);

				if (preparedStatement != null) {

					// Step3.Setting the values to place holder
					preparedStatement.setInt(1, id);

					// Step4.Executing the que
					resultSet = preparedStatement.executeQuery();

					if (resultSet != null) {

						// Step5.Processing the query
						if (resultSet.next()) {
							System.out.println("The age is :: " + resultSet.getString("age"));
							System.out.println("The age is :: " + resultSet.getString(1).substring(2, 4));
						} else {
							System.out.println("Record not avialable for the given id:: " + id);
						}

					}

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// Step5.Closing the jdbc resources
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException sqlException) {
				System.out.println("The cause of the exception is ::" + sqlException.getMessage());
			}

			try {
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException sqlException) {
				System.out.println("The cause of the exception is ::" + sqlException.getMessage());
			}

		}

	}

}
