package com.abc.mainapp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.abc.util.JdbcUtil;

public class TransactionManagementApp {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		Scanner scanner = null;
		int srcAccNo = 0;
		int destAccNo = 0;
		int balance = 0;
		boolean flag = false;

		try {
			scanner = new Scanner(System.in);
			if (scanner != null) {
				System.out.print("Enter the source accNo:: ");
				srcAccNo = scanner.nextInt();

				System.out.print("Enter the destination accNo:: ");
				destAccNo = scanner.nextInt();

				System.out.print("Enter the amount to be transferred:: ");
				balance = scanner.nextInt();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		connection = JdbcUtil.getConnection();
		if (connection != null) {
			try {
				// Disabling the AutoCommit nature of Connection object
				connection.setAutoCommit(false);

				statement = connection.createStatement();

				if (statement != null) {

					String HDFCQuery = "update HDFC set balance=balance-" + balance + "  where accNo=" + srcAccNo;
					String ICCIQuery = "update ICICI set balance=balance+" + balance + " where accNo=" + destAccNo;
					System.out.println(HDFCQuery);
					System.out.println(ICCIQuery);
					
					statement.addBatch(HDFCQuery);
					statement.addBatch(ICCIQuery);

					int[] results = statement.executeBatch();

					for (int row : results) {
						if (row == 0) {
							// Error in the execution by DBE
							flag = true;
							break;
						}
					}

				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (scanner != null) {
					scanner.close();
				}

				try {
					// Check if the flag value is true or not
					if (flag == true) {
						// Some problem so rever the operations
						connection.rollback();
						System.out.println("Fund transfer not succesfull.......");
					} else {
						// Succesfull so complete the operations
						connection.commit();
						System.out.println("Fund transfer succesfull..........");
					}
					if (statement != null) {
						statement.close();
					}

					// Closing the connection object
					JdbcUtil.closeConnection(connection);


				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		}

	}

}
