package com.abc.mainapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author AGasthya WAP to check whether the login credentials is valid or not
 *
 */
public class StatementSQLInjectinApp {

	public static void main(String[] args) {

		// Resources used in the application
		FileInputStream fileInputStream = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		Scanner scanner = null;

		// local variables used in the applciation
		String uname = null;
		String pwd = null;

		// Took the file name from the command line
		String fileName = "D:\\Advancedjavapgms\\PropertiesFileApproach\\src\\com\\abc\\resources\\" + args[0];
		System.out.println(fileName);

		try {

			scanner = new Scanner(System.in);

			if (scanner != null) {

				// Taking the inputs from the user to execute the query

				System.out.print("Enter the username:: ");
				uname = scanner.next();

				System.out.print("Enter the password:: ");
				pwd = scanner.next();
			}

			// Get the information from the fileName to java code
			fileInputStream = new FileInputStream(fileName);

			// Create the object of Properties file to get the data
			Properties properties = new Properties();
			properties.load(fileInputStream);

			// Use keys of properties file to get the value
			String url = properties.getProperty("jdbc.url");
			String username = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.pasword");

			System.out.println("The url is : " + url);
			System.out.println("The username is : " + username);
			System.out.println("The password is : " + password);

			// Step1.Establish the connection b/w java app and database
			connection = DriverManager.getConnection(url, username, password);

			if (connection != null) {

				// Step2.Creating the statement object to sent the query to DB
				statement = connection.createStatement();

				if (statement != null) {

					String SQLSELECTQUERY = "select count(*) from login where username='" + uname + "' and password='"
							+ pwd + "'";
					System.out.println("Query executed by DBE is :: " + SQLSELECTQUERY);

					// Step3.Execute the query by sending to DBE
					resultSet = statement.executeQuery(SQLSELECTQUERY);

					// Step4.Processing the resultSet object
					if (resultSet.next() != false) {
						int rowCount = resultSet.getInt(1);
						if (rowCount == 0) {
							System.out.println("Login credentails are incorrect");
						} else {
							System.out.println("Login succesfull!!!");
						}

					}

				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Closing iostream resource
			try {

				if (fileInputStream != null) {
					fileInputStream.close();
				}

			} catch (Exception e) {
				System.out.println("The cause of the exception is : " + e.getMessage());
			}
			// Closing scanner resource
			try {
				if (scanner != null) {
					scanner.close();
				}

			} catch (Exception exception) {
				System.out.println("The cause of the exception is ::" + exception.getMessage());
			}

			// Step5.Closing the jdbc resources

			try {
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException sqlException) {
				System.out.println("The cause of the exception is ::" + sqlException.getMessage());
			}
			try {
				if (statement != null) {
					statement.close();
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
