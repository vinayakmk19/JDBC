package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlDateInsertApp {

	private static final String SQLINSERTQUERY = "insert into andhrabank(name,accNo,balance) values(?,?,?)";

	public static void main(String[] args) {

		// Resources used in the application
		Connection mysqlConnection = null;
		Connection oracleConnection = null;
		ResultSet oracleResultSet = null;
		PreparedStatement mysqlPreparedStatement = null;
		Statement oracleStatement = null;

		// local variable used in the application
		String oracleUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String oracleUser = "system";
		String oraclePwd = "root123";

		String mysqlUrl = "jdbc:mysql:///abc";
		String mysqlUser = "root";
		String mysqlPwd = "root123";

		try {
			oracleConnection = DriverManager.getConnection(oracleUrl, oracleUser, oraclePwd);
			mysqlConnection = DriverManager.getConnection(mysqlUrl, mysqlUser, mysqlPwd);

			if (oracleConnection != null && mysqlConnection != null) {

				oracleStatement = oracleConnection.createStatement();

				if (oracleStatement != null) {

					String sqlSelectQuery = "select name,accno,balance from unionbank";
					oracleResultSet = oracleStatement.executeQuery(sqlSelectQuery);

					mysqlPreparedStatement = mysqlConnection.prepareStatement(SQLINSERTQUERY);

					if (oracleResultSet != null && mysqlPreparedStatement != null) {
						int count = 0;
						while (oracleResultSet.next()) {

							String name = oracleResultSet.getString(1);
							int accNo = oracleResultSet.getInt(2);
							float balance = oracleResultSet.getFloat(3);

							mysqlPreparedStatement.setString(1, name);
							mysqlPreparedStatement.setInt(2, accNo);
							mysqlPreparedStatement.setFloat(3, balance);

							mysqlPreparedStatement.executeUpdate();
							count++;
						}
						System.out.println(count + " records  Transfered from Union Bank to AndhraBank...");

					}

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (oracleStatement != null) {
					oracleStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (oracleResultSet != null) {
					oracleResultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (mysqlPreparedStatement != null) {
					mysqlPreparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (oracleConnection != null) {
					oracleConnection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (mysqlConnection != null) {
					mysqlConnection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
