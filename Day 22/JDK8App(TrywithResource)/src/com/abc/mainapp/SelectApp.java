package com.abc.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectApp {

	public static void main(String[] args) {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql:///abc", "root", "root123")) {

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

	}

}
