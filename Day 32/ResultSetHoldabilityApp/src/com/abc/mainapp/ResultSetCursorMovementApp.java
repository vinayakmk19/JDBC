package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetCursorMovementApp {

	public static void main(String[] args) throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:mysql:///abc", "root", "root123");

		connection.setAutoCommit(false);

		Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE,
				ResultSet.CLOSE_CURSORS_AT_COMMIT);

		ResultSet resultSet = statement.executeQuery("select * from student");
		System.out.println("SID\tSNAME\tSAGE");
		System.out.println("---------------------------------");
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
		}
		connection.commit();// Connection object is closed but still reusltset object can be used for CRUD
							// operations

		System.out.println();
		int row = 3;

		resultSet.absolute(row);
		System.out.println("Cursor moved to :: " + row + " row!!!");

		resultSet.updateString(2, "lara");
		resultSet.updateRow();

		connection.commit();// Call to make the changes to happen on the Database permanently

		System.out.println("Row is updated check the database for the result!!!!!");

	}

}
