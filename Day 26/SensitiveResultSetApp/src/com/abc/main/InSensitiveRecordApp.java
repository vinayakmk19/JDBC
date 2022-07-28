package com.abc.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//To demonstrate Updatable Result (Performing insert,update,delete through ResultSet Object)
public class InSensitiveRecordApp {

	public static void main(String[] args) {

		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system",
				"root123")) {
			
			

			try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE)) {

				try (ResultSet resultSet = statement.executeQuery("select SID,SNAME,SAGE,SADDRESS from student")) {

					System.out.println("Before Updation in the table...");
					System.out.println("ID\tName\tAge\tAddress\t");
					while (resultSet.next()) {

						System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t"
								+ resultSet.getString(3) + "\t" + resultSet.getString(4));

					}

					System.out.println("Application has paused!!! Do some changes in the Database table");

					System.in.read();

					System.out.println("After manipulation in the table through Offline Mode...");

					// Take the cursor to the begining of the Row
					resultSet.beforeFirst();

					System.out.println("ID\tName\tAge\tAddress\t");
					while (resultSet.next()) {

						System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t"
								+ resultSet.getString(3) + "\t" + resultSet.getString(4));

					}

				} catch (IOException e) {
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
