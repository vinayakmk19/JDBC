package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectApp {

	public static void main(String[] args) throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:mysql:///abc", "root", "root123");

		try (connection) {

			if (connection != null) {

				try (Statement statement = connection.createStatement()) {

					if (statement != null) {

						try (ResultSet resultSet = statement.executeQuery("select * from product")) {

							if (resultSet != null) {

								System.out.println("PID" + "\t" + "PNAME" + "\t" + "PCOST" + "\t");
								while (resultSet.next() != false) {
									int pid = resultSet.getInt(1);
									String pname = resultSet.getString(2);
									int pcost = resultSet.getInt(3);
									System.out.println(pid + "\t" + pname + "\t" + pcost);
								} // while

							} // if

						} // resultSet will be closed automatically
						catch (SQLException e) {
							e.printStackTrace();
						}
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

		System.out.println("Implementation class object of Connection is :: " + connection.getClass().getName());

		Statement statement = connection.createStatement();// java.sql.SQLNonTransientConnectionException ::Connection
															// is closed
		System.out.println("Implementation class object of Statement is :: " + statement.getClass().getName());

	}

}
