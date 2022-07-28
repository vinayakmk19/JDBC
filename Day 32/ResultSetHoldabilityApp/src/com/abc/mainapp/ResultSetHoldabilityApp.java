package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetHoldabilityApp {
	public static void main(String[] args) throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "root123");

		DatabaseMetaData databaseMetaData = connection.getMetaData();
		Statement statement = connection.createStatement();
		System.out.println("ResultSet Holdability:: " + statement.getResultSetHoldability());
		System.out.println("ResultSet type:: " + statement.getResultSetType());
		System.out.println("ResultSet concurrency:: " + statement.getResultSetConcurrency());
		System.out.println("Does it supports ResultSet.CLOSE_CURSORS_AT_COMMIT :"
				+ databaseMetaData.supportsResultSetHoldability(2));
	}

}
