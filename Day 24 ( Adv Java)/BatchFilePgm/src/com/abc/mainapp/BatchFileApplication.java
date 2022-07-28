package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchFileApplication {

	public static void main(String[] args) {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql:///abc", "root", "root123")) {

			if (connection != null) {

				try (Statement statement = connection.createStatement()) {

					if (statement != null) {

						statement.addBatch("insert into student values(18,'kohli',35)");
						statement.addBatch("insert into student values(9,'lara',51)");
						statement.addBatch("update student set sname='sachintendulkar' where sid=10");
						statement.addBatch("delete from student where sid=14");

						int[] result = statement.executeBatch();

						int total = 0;
						for (int row : result) {
							total += row;
						}
						System.out.println("No of records affected through batchfiles is :: " + total);

					}

				} // statement object will be closed automatically
				catch (SQLException e) {
					e.printStackTrace();
				}

			}

		} // Connection object will be closed automatically
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
