package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcelInsertApp {

	// select colname1,colname2,... from workbook.sheetName
	private static final String EXCEL_SELECT = "select * from STUDENT.STUDENTRECORDS";

	public static void main(String[] args) {

		try (Connection connection = DriverManager.getConnection("jdbc:Excel:///D:\\AdvancedJavaBatch")) {
			System.out.println("Implementatio class name is :: " + connection.getClass().getName());

			try (Statement statement = connection.createStatement()) {
				System.out.println("Implementatin class name is :: " + statement.getClass().getName());

				try (ResultSet resultSet = statement.executeQuery(EXCEL_SELECT)) {
					System.out.println("Implementation class name is :: " + resultSet.getClass().getName());

					System.out.println("ID\tName\tAge\tAddress");
					while (resultSet.next()) {
						System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t"
								+ resultSet.getInt(3) + "\t" + resultSet.getString(4));

					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
