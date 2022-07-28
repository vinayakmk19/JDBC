package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataApplicationRetrievingRecords {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResultSetMetaData resultSetMetaData = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql:///abc", "root", "root123");

			if (connection != null) {

				statement = connection.createStatement();

				if (statement != null) {
					resultSet = statement.executeQuery("select * from product");
					if (resultSet != null) {

						resultSetMetaData = resultSet.getMetaData();

						if (resultSetMetaData != null) {

							while (resultSet.next() != false) {

								for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
									System.out.print(resultSet.getString(i) + " ");
								}
								System.out.println();
							}

						}

					}

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
