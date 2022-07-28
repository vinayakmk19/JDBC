package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class MysqlMainAppRandomGen {

	private static String SQLINSERTQUERY = "insert into person(id,name,age,email) values(?,?,?,?)";

	public static void main(String[] args) {

		// Resources used in the application

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Scanner scanner = null;

		// local variables used in the applciation
		String name = null;
		int age = 0;
		String email = null;

		// Database credentials
		String url = "jdbc:mysql:///abc";
		String user = "root";
		String password = "root123";

		try {

			scanner = new Scanner(System.in);

			if (scanner != null) {

				// Taking the inputs from the user to execute the query

				

				System.out.print("Enter the name:: ");
				name = scanner.next();

				System.out.print("Enter the age:: ");
				age = scanner.nextInt();

				System.out.print("Enter the mailid:: ");
				email = scanner.next();

			}

			// Step1.Establish the connection b/w java app and database
			connection = DriverManager.getConnection(url, user, password);

			if (connection != null) {

				// Step2.Create the PreparedStatement object
				preparedStatement = connection.prepareStatement(SQLINSERTQUERY);

				if (preparedStatement != null) {

					// Step3.Setting the values to place holder
					int randomNumber = new Random().nextInt(999);
					System.out.println("Random number as a id is :: "+randomNumber);
					
					
					preparedStatement.setInt(1, randomNumber);
					preparedStatement.setString(2, name);
					preparedStatement.setInt(3, age);
					preparedStatement.setString(4, email);

					// Step4.Executing the PreparedStatement object
					int rowAffected = preparedStatement.executeUpdate();

					if (rowAffected == 0) {
						System.out.println("Insertion failed");
					} else {
						System.out.println("Row inserted Succesfully!!!!");
					}

				}

			}

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

			try {
				if (scanner != null) {
					scanner.close();
				}

			} catch (Exception exception) {
				System.out.println("The cause of the exception is ::" + exception.getMessage());
			}

		}

	}

}
