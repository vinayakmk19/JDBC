package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMetaDataApplication {

	public static void main(String[] args) throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:mysql:///abc", "root", "root123");

		DatabaseMetaData dbMetaData = connection.getMetaData();

		System.out.println("The implementation class object of Connection is :: " + connection.getClass().getName());
		System.out.println(
				"The implementation class object of DatabaseMetaData is :: " + dbMetaData.getClass().getName());

		if (dbMetaData != null) {

			System.out.println("DatabaseEngine name is :: " + dbMetaData.getDatabaseProductName());
			System.out.println("Databaseversion is :: " + dbMetaData.getDatabaseProductVersion());
			System.out.println("Driver Version is:: " + dbMetaData.getDriverVersion());
			System.out.println("Database Major Version is :: " + dbMetaData.getDatabaseMajorVersion());
			System.out.println("Database Minor Version is :: " + dbMetaData.getDatabaseMinorVersion());
			System.out.println("Database SQLKeywords is :: " + dbMetaData.getSQLKeywords());
			System.out.println("Database Math functions are:: " + dbMetaData.getNumericFunctions());
			System.out.println("Database DateTime functions are:: " + dbMetaData.getTimeDateFunctions());
			System.out.println("Max columns in a  table:: " + dbMetaData.getMaxColumnsInTable());
			System.out.println("Max Columns during Select:: " + dbMetaData.getMaxColumnsInSelect());
			System.out.println("Does DB supports StoredProcedures:: " + dbMetaData.supportsStoredProcedures());
			System.out.println("Max Row size:: " + dbMetaData.getMaxRowSize());

		}

	}

}
