package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CSVInsertApp {

	// insert into filename.csv(?,?...)
	private static final String CSV_INSERT = "insert into sample.csv values(?,?,?,?)";

	public static void main(String[] args) {

		Scanner scanner = null;
		int id = 0;
		String name = null;
		int age = 0;
		String address = null;

		try {
			scanner = new Scanner(System.in);

			System.out.print("Enter the id:: ");
			id = scanner.nextInt();

			System.out.print("Enter the name:: ");
			name = scanner.next();

			System.out.print("Enter the age:: ");
			age = scanner.nextInt();

			System.out.print("Enter the address:: ");
			address = scanner.next();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}

		}

		try (Connection connection = DriverManager.getConnection("jdbc:Text:///D:\\AdvancedJavaBatch")) {

			try (PreparedStatement preparedStatement = connection.prepareStatement(CSV_INSERT)) {

				if (preparedStatement != null) {

					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, name);
					preparedStatement.setInt(3, age);
					preparedStatement.setString(4, address);

					int rowAffected = preparedStatement.executeUpdate();

					if (rowAffected == 0) {
						System.out.println("Record not inserted");
					} else {
						System.out.println(rowAffected + " Record Inserted Succesfully!!!!!!");
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
