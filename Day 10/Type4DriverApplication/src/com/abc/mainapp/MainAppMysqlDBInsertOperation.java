package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 
 * @author AGasthya WAP to perform Insert(Create) operation on the table called
 *         student.
 *
 */
public class MainAppMysqlDBInsertOperation {

	public static void main(String[] args) {

		// Resources used in the application
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Scanner scanner = null;

		// Variables associated with student object
		int sid = 0;
		String sname = null;
		int sage = 0;

		// UrlPattern="protocolName:subprotocolName:<ipaddres_db_Machine>:<port_no>/<dbName>"
		String url = "jdbc:mysql://localhost:3306/abc";
		String user = "root";
		String password = "root123";

		try {

			scanner = new Scanner(System.in);
			if (scanner != null) {

				// Taking the inputs from the keyboard
				System.out.print("Enter the student id: ");
				sid = scanner.nextInt();

				System.out.print("Enter the name: ");
				sname = scanner.next();

				System.out.print("Enter the sage:");
				sage = scanner.nextInt();
			}

			// Step1.Establishing the connection b/w java application and database(MySQL)
			connection = DriverManager.getConnection(url, user, password);

			if (connection != null) {
				System.out.println("Connected to database succesfully!!!!");

				// Step2.Create an Statement Object as per the user needs.
				statement = connection.createStatement();

				if (statement != null) {

					String sqlInsertQuery = "insert into (`sid`,`sname`,`sage`) values(" + sid + ",'" + sname + "',"
							+ sage + ")";
					System.out.println(sqlInsertQuery);

					// Step3:Executing NonSelect Query

					int rowAffected = statement.executeUpdate(sqlInsertQuery);

					// Step4:Processing the result generated by DBE
					if (rowAffected != -1) {
						System.out.println("No of records inserted to database is ::" + rowAffected);
					} else {
						System.out.println("Insertion is failure");
					}

				}

			}

		} catch (

		SQLException sqlException) {

			if (sqlException.getErrorCode() == 1062) {
				System.out.println("Duplicate entries are not  allowed please retry!!!");
			} else if (sqlException.getErrorCode() == 1054) {
				System.out.println("Properly specify the column values");
			} else if (sqlException.getErrorCode() == 1064) {
				System.out.println("SQL Syntax problem");
			} else {
				System.out.println("Some unkonwn SQLExeption");
			}

		} finally {
			// Step5.Closing jdbc resources
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

			// Closing IOStream resource
			try {

				if (scanner != null) {
					scanner.close();
				}

			} catch (Exception exception) {
				System.out.println(exception.getMessage());
			}

		}

	}

}