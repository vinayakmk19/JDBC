package com.abc.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//To demonstrate Updatable Result (Performing insert,update,delete through ResultSet Object)
public class DeleteRecordApp {

	public static void main(String[] args) {

		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system",
				"root123")) {

			try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE)) {

				try (ResultSet resultSet = statement.executeQuery("select SID,SNAME,SAGE,SADDRESS from student")) {

					System.out.println("ID\tName\tAge\tAddress\t");
					while (resultSet.next()) {

						System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t"
								+ resultSet.getString(3) + "\t" + resultSet.getString(4));

					}

					int rowNo = 6;

					// Take the cursor to specified row for updation
					resultSet.absolute(rowNo);

					// Delete the row as specified by the user
					resultSet.deleteRow();

					System.out.println("Record deleted Succesfully!!!!");

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
