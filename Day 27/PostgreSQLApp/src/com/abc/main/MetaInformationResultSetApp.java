package com.abc.main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetaInformationResultSetApp {

	public static void main(String[] args) {

		String url = "jdbc:postgresql:abc";
		String user = "postgres";
		String password = "root123";
		
		
		try (Connection connection = DriverManager.getConnection(url, user, password)) {

			DatabaseMetaData databaseMetaData = connection.getMetaData();

			// default Mode
			System.out.println("ResultSet Object Forward and ReadOnly:: " + databaseMetaData
					.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY));

			// ResultSet Object:: Insensitve(NO changes to db table) and it should be used
			// only for Reading
			System.out.println("ResultSet Object SCROLLABLE(insensitive) and ReadOnly:: " + databaseMetaData
					.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY));

			// ResultSet Object:: Sensitive(changes to db table reflection should happen)
			// and it can be use for CRUD
			System.out.println("ResultSet Object SCROLLABLE(sensitive) and Updatable:: " + databaseMetaData
					.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE));

			// ResultSet Object:: insensitive(No changes made to db Table) and it will be
			// used for CRUD
			System.out.println("ResultSet Object SCROLLABLE(insensitive) and Updatable:: " + databaseMetaData
					.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE));

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
