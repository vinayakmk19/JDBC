package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 
 * @author AGasthya WAP to perform update operation on the table called student
 *         where studen id is given by the user(update the age information).
 *
 */
public class MainAppMysqlDBUpdateOperation {

	public static void main(String[] args) {

		// Resources used in the application
		Connection connection = null;
		Statement statement = null;
		Scanner scanner = null;

		// Variables associated with student object
		int sid = 0;
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

				System.out.print("Enter the age updation years:");
				sage = scanner.nextInt();
			}

			// Step1.Establishing the connection b/w java application and database(MySQL)
			connection = DriverManager.getConnection(url, user, password);

			if (connection != null) {
				System.out.println("Connected to database succesfully!!!!");

				// Step2.Create an Statement Object as per the user needs.
				statement = connection.createStatement();

				if (statement != null) {

					String sqlUpdateQuery = "update `student` set `sage`=sage+" + sage + " where `sid`=" + sid;
					System.out.println("The query executed by DBE is : " + sqlUpdateQuery);

					// Step3:Executing NonSelect Query int rowAffected = int rowAffected =
					int rowAffected = statement.executeUpdate(sqlUpdateQuery);

					// Step4:Processing the result generated by DBE
					if (rowAffected != -1) {
						System.out.println("No of records updated in the  database is ::" + rowAffected);
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
