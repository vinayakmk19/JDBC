package com.abc.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStatementInsertRecordApp {

	private static final String INSERT_QUERY = "insert into student values(nextval('sid_sequence'),?,?,?)";

	public static void main(String[] args) {

		// local variables
		String sname = null;
		int sage = 0;
		String saddress = null;

		Scanner scanner = null;

		// jdbc credentials
		String jdbcUrl = "jdbc:postgresql:///abc";
		String userName = "postgres";
		String password = "root123";

		try {
			scanner = new Scanner(System.in);

			System.out.print("Enter the name:: ");
			sname = scanner.next();

			System.out.print("Enter the age:: ");
			sage = scanner.nextInt();

			System.out.print("Enter the address:: ");
			saddress = scanner.next();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		try (Connection connection = DriverManager.getConnection(jdbcUrl, userName, password)) {

			try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

				// set the values
				preparedStatement.setString(1, sname);
				preparedStatement.setInt(2, sage);
				preparedStatement.setString(3, saddress);

				// exeucte the query
				int rowAffected = preparedStatement.executeUpdate();

				// process the information
				if (rowAffected == 0) {
					System.out.println("NO records were inserted!!!!");
				} else {
					System.out.println("Records are inserted.....");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
