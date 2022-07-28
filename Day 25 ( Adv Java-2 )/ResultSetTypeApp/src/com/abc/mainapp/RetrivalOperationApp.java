package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RetrivalOperationApp {

	public static void main(String[] args) {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql:///abc", "root", "root123")) {

			try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)) {

				try (ResultSet resultSet = statement.executeQuery("select * from student")) {

					System.out.println("Forward Direction Retrival of Records");
					int rowNo = 3;
					while (resultSet.next()) {

						System.out.println(resultSet.getRow() + "\t" + resultSet.getInt(1) + "\t"
								+ resultSet.getString(2) + "\t" + resultSet.getInt(rowNo));
					}

					System.out.println("------------------------------------------------------");

					System.out.println("BackWard Direction Retrieving the records");
					while (resultSet.previous()) {
						System.out.println(
								resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(rowNo));
					}

					System.out.println("----------------------");
					resultSet.last();
					System.out.println("Printing Last Record");
					System.out.println(
							resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(rowNo));

					System.out.println("---------------------------");
					System.out.println("Printing first Record");
					resultSet.first();
					System.out.println(
							resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(rowNo));

					System.out.println("--------------------------------------");

					resultSet.absolute(rowNo);
					System.out.println("Printing record form RowNo:: " + rowNo);
					
					
					while (resultSet.next()) {
						System.out.println(
								resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(rowNo));
						
					}
					System.out.println("--------------------------------------------");
					rowNo=-3;
					resultSet.absolute(rowNo);
					System.out.println("Printing record form RowNo:: " + rowNo);
					while (resultSet.previous()) {
						System.out.println(
								resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3));

					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
