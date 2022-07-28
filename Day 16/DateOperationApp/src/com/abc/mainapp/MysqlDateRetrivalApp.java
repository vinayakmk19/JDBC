package com.abc.mainapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;

public class MysqlDateRetrivalApp {

	private static String SQLSELECTQUERY = "select * from employee where id=?";

	public static void main(String[] args) {

		// Resources used in the application
		FileInputStream fileInputStream = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Scanner scanner = null;
		SimpleDateFormat sdf = null;
		java.sql.Date sqlDob = null, sqlDoj = null, sqlDom = null;

		// local variables used in the applciation
		int id = 0;
		String name = null;
		String sdob = null;
		String sdom = null;
		String sdoj = null;
		String address = null;

		// Took the file name from the command line
		String fileName = "D:\\Advancedjavapgms\\PropertiesFileApproach\\src\\com\\abc\\resources\\" + args[0];
		System.out.println(fileName);

		try {

			scanner = new Scanner(System.in);

			if (scanner != null) {

				// Taking the inputs from the user to execute the query

				System.out.print("Enter the id of the student :: ");
				id = scanner.nextInt();

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

				// Step2.Create the PreparedStatement object
				preparedStatement = connection.prepareStatement(SQLSELECTQUERY);

				if (preparedStatement != null) {
					// Setting the value to dynamic params(?)
					preparedStatement.setInt(1, id);

					// Step3.Execute the Query
					resultSet = preparedStatement.executeQuery();

					if (resultSet != null) {

						if (resultSet.next() != false) {

							id = resultSet.getInt(1);
							name = resultSet.getString(2);

							sqlDob = resultSet.getDate(3);
							sqlDom = resultSet.getDate(4);
							sqlDoj = resultSet.getDate(5);

							address = resultSet.getString(6);

							sdf = new SimpleDateFormat("yyyy-MM-dd");

							sdob = sdf.format(sqlDob);
							sdom = sdf.format(sqlDom);
							sdoj = sdf.format(sqlDoj);
							
							System.out.println("---------------------------------------------------------------");

							System.out.println("id :: " + id + " Name:: " + name + " dob:: " + sdob + " dom:: " + sdom
									+ " doj:: " + sdoj + " Adddress:: " + address);
							
							System.out.println("----------------------------------------------------------------");

							System.out.println("id :: " + id + " Name:: " + name + " dob:: " + sqlDob + " dom:: "
									+ sqlDom + " doj:: " + sqlDoj + " Adddress:: " + address);

						} else {
							System.out.println("Record not avialable for the given id:: " + id);
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
