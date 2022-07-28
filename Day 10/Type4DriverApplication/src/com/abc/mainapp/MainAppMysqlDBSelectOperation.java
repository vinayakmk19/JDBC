package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainAppMysqlDBSelectOperation {

	public static void main(String[] args) {

		// Resources used in the application
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		// UrlPattern="protocolName:subprotocolName:<ipaddres_db_Machine>:<port_no>/<dbName>"
		String url = "jdbc:mysql://localhost:3306/abc";
		String user = "root";
		String password = "root123";

		try {

			// Step1.Establishing the connection b/w java application and database(MySQL)
			connection = DriverManager.getConnection(url, user, password);

			if (connection != null) {
				System.out.println("Connected to database succesfully!!!!");

				// Step2.Create an Statement Object as per the user needs.
				statement = connection.createStatement();

				if (statement != null) {

					String sqlQuery = "select * from `Student` where `sid`=10";

					// Step3.Execute the query as per the requirement
					resultSet = statement.executeQuery(sqlQuery);

					if (resultSet != null) {

						if (resultSet.next() != false) {

							// Step4.Retrieving records
							System.out.println("SID\t SNAME\t SAGE\t");

							/*int sid = resultSet.getInt("SID");
							String sname = resultSet.getString("SNAME");
							int sage = resultSet.getInt("SAGE");*/
							
							
							String sid=resultSet.getString(1);
							String sname=resultSet.getString(2);
							String sage=resultSet.getString(3);
							
							System.out.println(sid + "\t" + sname + "\t" + sage);
						} else {
							System.out.println("Record not available for the given sid");
						}
					}

				}

			}

		} catch (SQLException e) {
			System.out.println("The cause of the exception is ::" + e.getMessage());
		} finally {
			// Step5.Closing resources
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
