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

public class ExeucteMethodLogicApp {

	public static void main(String[] args) {

		// Resources used in the application
		FileInputStream fileInputStream = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		// Took the file name from the command line
		String fileName = "D:\\Advancedjavapgms\\PropertiesFileApproach\\src\\com\\abc\\resources\\" + args[0];
		System.out.println(fileName);

		try {
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
				// Step2.Create the statement object
				statement = connection.createStatement();

				if (statement != null) {
					String sqlDeleteQuery = "delete from student where sid=10";
					boolean flag = statement.execute(sqlDeleteQuery);

					if (flag == true) {
						System.out.println("Select Query is getting executed");

						resultSet = statement.getResultSet();

						if (resultSet != null) {

							System.out.println("SID\t SNAME\t SAGE");

							while (resultSet.next() != false) {
								System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t"
										+ resultSet.getString(3));
							}

						}

					} else {
						System.out.println("DML query is getting executed");

						int rowAffected = statement.getUpdateCount();
						
						if (rowAffected == 0) {
							System.out.println("Record not available for deletion");
						} else {
							System.out.println("Record  got deleted succesfully");
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
