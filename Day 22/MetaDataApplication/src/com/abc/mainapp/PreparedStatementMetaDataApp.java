package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementMetaDataApp {

	public static final String INSERT_QUERY = "insert into products(?,?,?,?)";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ParameterMetaData parameterMetaData = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql:///abc", "root", "root123");

			if (connection != null) {

				preparedStatement = connection.prepareStatement(INSERT_QUERY);

				if (preparedStatement != null) {
					parameterMetaData = preparedStatement.getParameterMetaData();
					System.out.println("Implementation class object of ParameterMetaData is :: "
							+ parameterMetaData.getClass().getName());

					if (parameterMetaData != null) {

						System.out.println("No of parameters is :: " + parameterMetaData.getParameterCount());

						for (int i = 1; i <= parameterMetaData.getParameterCount(); i++) {
							System.out.println("Parameter DataType is :: " + parameterMetaData.getParameterTypeName(i));
						}

					}

				}

			}

		} catch (SQLException e) {
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

		}

	}

}
