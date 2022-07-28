package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetInfoApp {

	public static void main(String[] args) {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql:///abc", "root", "root123")) {

			DatabaseMetaData databaseMetaData = connection.getMetaData();

			System.out.println("ResultSet Type only Read Opeation:: " + databaseMetaData.supportsResultSetType(1008));
			System.out.println("ResultSet only Updatable Operation:: " + databaseMetaData.supportsResultSetType(1007));

			System.out.println();

			System.out.println("ResultSet in BackWard Direction with ReadOnly Operation:: " + databaseMetaData
					.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY));

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
