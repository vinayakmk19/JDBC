package com.abc.mainapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class MysqlDateInsertApp {

	private static String SQLINSERTQUERY = "insert into employee values(?,?,?,?,?,?)";

	public static void main(String[] args) {

		// Resources used in the application
		FileInputStream fileInputStream = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Scanner scanner = null;
		SimpleDateFormat sdf1 = null, sdf2 = null;
		Date udob = null, udom = null;
		java.sql.Date sqlDob = null, sqlDoj = null, sqlDom = null;

		// local variables used in the applciation
		int id = 0;
		String name = null;
		String dob = null;
		String dom = null;
		String doj = null;
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

				System.out.print("Enter the name:: ");
				name = scanner.next();

				System.out.print("Enter the dob(dd-MM-yyyy):: ");
				dob = scanner.next();

				System.out.print("Enter the dom(MM-dd-yyyy):: ");
				dom = scanner.next();

				System.out.print("Enter the doj(yyyy-MM-dd):: ");
				doj = scanner.next();

				System.out.print("Enter the address:: ");
				address = scanner.next();

				sdf1 = new SimpleDateFormat("dd-MM-yyyy");

				if (sdf1 != null) {

					udob = sdf1.parse(dob);

					if (udob != null) {
						sqlDob = new java.sql.Date(udob.getTime());
					}

				}

				sdf2 = new SimpleDateFormat("MM-dd-yyyy");
				if (sdf2 != null) {

					udom = sdf2.parse(dom);

					if (udom != null) {
						sqlDom = new java.sql.Date(udom.getTime());
					}

				}

				sqlDoj = java.sql.Date.valueOf(doj);

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
				preparedStatement = connection.prepareStatement(SQLINSERTQUERY);

				if (preparedStatement != null) {

					// Step3.Setting the values to place holder
					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, name);
					preparedStatement.setDate(3, sqlDob);
					preparedStatement.setDate(4, sqlDom);
					preparedStatement.setDate(5, sqlDoj);

					preparedStatement.setString(6, address);

					// Step4.Executing the PreparedStatement object
					int rowAffected = preparedStatement.executeUpdate();

					if (rowAffected == 0) {
						System.out.println("Insertion failed");
					} else {
						System.out.println("Row inserted Succesfully!!!!");
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
