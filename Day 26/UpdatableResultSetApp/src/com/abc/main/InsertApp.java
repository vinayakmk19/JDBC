package com.abc.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//To demonstrate Updatable Result (Performing insert,update,delete through ResultSet Object)
public class InsertApp {

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

					// request him to create one empty row
					resultSet.moveToInsertRow();

					// fill the row with the values
					resultSet.updateInt(1, 19);
					resultSet.updateString(2, "rahul");
					resultSet.updateInt(3, 49);
					resultSet.updateString(4, "RR");

					// set it permanently to the database.
					resultSet.insertRow();
					System.out.println("Record inserted succesfully");

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
