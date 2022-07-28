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

					String sqlQuery = "select * from `Student`";

					// Step3.Execute the query as per the requirement
					resultSet = statement.executeQuery(sqlQuery);

					if (resultSet != null) {

						if (resultSet.getFetchSize() >= 0) {

							// Step4.Perform Retrieval operation on the data retrieved from the database
							System.out.println("SID\t SNAME\t SAGE\t");
							while (resultSet.next() != false) {

								int sid = resultSet.getInt(1);
								String sname = resultSet.getString(2);
								int sage = resultSet.getInt(3);

								System.out.println(sid + "\t" + sname + "\t" + sage);
							}

						} else {
							System.out.println("Sorry no records to display!!!!");
						}

					}

				}

			}

		} catch (SQLException e) {
			System.out.println("The cause of the exception is ::" + e.getMessage());
		} finally {
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
