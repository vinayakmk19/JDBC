package com.abc.mainapp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HtmlOutputApp {

	public static void main(String[] args) {

		// Resources used in the application
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;

		String mysqlUrl = "jdbc:mysql:///abc";
		String mysqlUser = "root";
		String mysqlPwd = "root123";
		String data = "";
		FileOutputStream fileOutputStream = null;

		try {

			// establishing the connection to mysql
			connection = DriverManager.getConnection(mysqlUrl, mysqlUser, mysqlPwd);

			if (connection != null) {
				// create statement object
				statement = connection.createStatement();

				if (statement != null) {
					// execute the query
					resultSet = statement.executeQuery("select * from andhrabank");

					if (resultSet != null) {

						data = data + "<html><head><title>bankdata</title></head><body>";
						data = data + "<table border='3' align='center'><tr><th>id</th><th>name</th><th>accNo</th><th>balance</th></tr>";

						while (resultSet.next() != false) {

							data = data + "<tr>" + "\n";
							data = data + "<td>" + resultSet.getInt(1) + "</td>";
							data = data + "<td>" + resultSet.getString(2) + "</td>";
							data = data + "<td>" + resultSet.getInt(3) + "</td>";
							data = data + "<td>" + resultSet.getFloat(4) + "</td>";
							data = data + "</tr>" + "\n";
						}
						data = data + "</table></body></html>";

					}

					// Creating a stream to write to html page
					String htmlFile = "bankinfo.html";
					fileOutputStream = new FileOutputStream(htmlFile);

					if (fileOutputStream != null) {
						
						// Convert the variable which holds the data into bytes for sending
						byte[] bytes = data.getBytes();

						// Data should be sent in the form of bytes(0101010)
						fileOutputStream.write(bytes);

						System.out.println("Data Transfered to a file called " + htmlFile);

					}

				}

			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
