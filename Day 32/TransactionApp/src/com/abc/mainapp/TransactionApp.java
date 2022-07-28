package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class TransactionApp {
	public static void main(String[] args) throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:mysql:///abc", "root", "root123");
		connection.setAutoCommit(false);

		Statement statement = connection.createStatement();

		int row1 = statement.executeUpdate("insert into student values(21,'Sidanth',23)");
		int row2 = statement.executeUpdate("insert into student values(22,'Babitha',22)");

		Savepoint sp = connection.setSavepoint();
		statement.executeUpdate("insert into student values(5,'Nitin',21)");
		System.out.println("Ooops!!!! Wrong data Insertion into the table.....");
		connection.rollback(sp);

		int row3 = statement.executeUpdate("insert into student values(24,'AnilKumar',21)");
		connection.releaseSavepoint(sp);
		connection.commit();
		System.out.println("No of records inserted is :: " + (row1 + row2 + row3));

	}

}
