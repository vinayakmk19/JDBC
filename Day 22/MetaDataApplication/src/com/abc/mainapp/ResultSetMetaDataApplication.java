package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataApplication {

	public static void main(String[] args) throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:mysql:///abc", "root", "root123");

		Statement statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery("select * from product");

		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		System.out.println("The implementation class object of ResultSetMetaData(I) is :: "
				+ resultSetMetaData.getClass().getName());
		System.out.println("------------------------------------------------------------------------------------");

		if (resultSetMetaData != null) {

			System.out.println("No of columns in the resultSet object is :: " + resultSetMetaData.getColumnCount());

			for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
				System.out.println(resultSetMetaData.getColumnLabel(i) + "\t" + resultSetMetaData.getColumnTypeName(i)
						+ "\t" + resultSetMetaData.getColumnDisplaySize(i) + "\t" + resultSetMetaData.isCaseSensitive(i)
						+ "\t" + resultSetMetaData.isAutoIncrement(i) + "\t" + resultSetMetaData.isNullable(i));
			}

		}

	}

}
