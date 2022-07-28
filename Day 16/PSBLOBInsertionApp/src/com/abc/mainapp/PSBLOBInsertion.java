package com.abc.mainapp;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSBLOBInsertion {

	private static String SQLINSERTQUERY = "insert into artist_info values(?,?,?,?,?)";

	public static void main(String[] args) {

		// Resources used in the application
		FileInputStream ifis = null;
		FileInputStream vfis = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Scanner scanner = null;

		// local variables used in the applciation
		int id = 0;
		String name = null;
		String address = null;
		String imagePath = null;
		String videoPath = null;
		String url = "jdbc:mysql://localhost:3306/abc";
		String username = "root";
		String password = "root123";

		try {

			scanner = new Scanner(System.in);

			if (scanner != null) {

				// Taking the inputs from the user to execute the query

				System.out.print("Enter the id of the student :: ");
				id = scanner.nextInt();

				System.out.print("Enter the name:: ");
				name = scanner.next();

				System.out.print("Enter the address:: ");
				address = scanner.next();

				System.out.print("Enter the location of the image:: ");
				imagePath = scanner.next();

				System.out.print("Enter the location of the video::");
				videoPath = scanner.next();

				ifis = new FileInputStream(imagePath);
				vfis=new FileInputStream(videoPath);
			}

			// Step1.Establish the connection b/w java app and database
			connection = DriverManager.getConnection(url, username, password);

			if (connection != null) {

				// Step2.Create the PreparedStatement object
				preparedStatement = connection.prepareStatement(SQLINSERTQUERY);

				if (preparedStatement != null) {

					if (ifis != null  && vfis!=null) {

						// Step3.Setting the values to place holder
						preparedStatement.setInt(1, id);
						preparedStatement.setString(2, name);
						preparedStatement.setString(3, address);

						// Setting binaryStream to preparedStatement object to get the data through fis.
						preparedStatement.setBinaryStream(4, ifis);
						preparedStatement.setBinaryStream(5, vfis);

						int rowAffected = preparedStatement.executeUpdate();

						if (rowAffected == 1) {
							System.out.println("Insertion Succesfull!!!!!");
						} else {
							System.out.println("Record failed to insert.....");
						}

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Closing iostream resource
			try {

				if (ifis != null) {
					ifis.close();
				}
				if (vfis != null) {
					vfis.close();
				}

			} catch (Exception e) {
				System.out.println("The cause of the exception is : " + e.getMessage());
			}

			// Closing scanner resource
			try {

				if (scanner != null) {
					scanner.close();
				}

			} catch (Exception e) {
				System.out.println("The cause of the exception is : " + e.getMessage());
			}

			// Step5.Closing the jdbc resources
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException sqlException) {
				System.out.println("The cause of the exception is ::" + sqlException.getMessage());
			}

			try {
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException sqlException) {
				System.out.println("The cause of the exception is ::" + sqlException.getMessage());
			}

		}

	}

}
