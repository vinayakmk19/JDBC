package com.abc.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectRecordApp {

	public static void main(String[] args) {

		String jdbcUrl = "jdbc:postgresql:///abc";
		String userName = "postgres";
		String password = "root123";
		try (Connection connection = DriverManager.getConnection(jdbcUrl, userName, password)) {

			try (Statement statement = connection.createStatement()) {

				try (ResultSet resultSet = statement.executeQuery("select SID,SNAME,SAGE,SADDRESS from student")) {

					System.out.println("ID\tName\tAge\tAddress\t");
					while (resultSet.next()) {

						System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t"
								+ resultSet.getString(3) + "\t" + resultSet.getString(4));

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
