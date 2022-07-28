package com.abc.mainapp;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PSBLOBRetrievalApp {

	private static String SQLSELECTQUERY = "select * from artist_info where id=?";

	public static void main(String[] args) {

		// Resources used in the application
		InputStream imageis = null;
		InputStream videois = null;
		FileOutputStream imageos = null;
		FileOutputStream videos = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Scanner scanner = null;

		// local variables used in the applciation
		int id = 0;
		String name = null;
		String address = null;

		// DBCredentials
		String url = "jdbc:mysql://localhost:3306/abc";
		String username = "root";
		String password = "root123";

		try {

			scanner = new Scanner(System.in);

			if (scanner != null) {

				// Taking the inputs from the user to execute the query

				System.out.print("Enter the id of the student :: ");
				id = scanner.nextInt();

			}

			// Step1.Establish the connection b/w java app and database
			connection = DriverManager.getConnection(url, username, password);

			if (connection != null) {

				// Step2.Create the PreparedStatement object
				preparedStatement = connection.prepareStatement(SQLSELECTQUERY);

				if (preparedStatement != null) {

					// set the preparedStatement value for ? params
					preparedStatement.setInt(1, id);

					// Step3.Executing the query
					resultSet = preparedStatement.executeQuery();

					if (resultSet != null) {
						if (resultSet.next() != false) {

							id = resultSet.getInt(1);
							name = resultSet.getString(2);
							address = resultSet.getString(3);
							imageis = resultSet.getBinaryStream(4);
							videois = resultSet.getBinaryStream(5);

							String imagePath = "sachin.jpg";
							String videoPath = "sample.mp4";

							imageos = new FileOutputStream(imagePath);
							videos = new FileOutputStream(videoPath);

							if (imageis != null && imageos != null) {

								// use the stream to read the data(image) coming from DBE
								int data = imageis.read();
								// loop until the data we are receiving is notequal to -1
								while (data != -1) {
									// using the output stream write image data to the desired location)
									imageos.write(data);
									data = imageis.read();
								}

								IOUtils.copy(videois, videos);

							}

							System.out.println("Id :: " + id + " Name:: " + name + "Address:: " + address);
							System.out.println("Image retreival is completed and saved as :: " + imagePath);
							System.out.println("vidoe retreival is completed and saved as :: " + videoPath);
						} else {
							System.out.println("Record not available for the given id:: " + id);
						}

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Closing iostream resource
			try {

				if (imageis != null) {
					imageis.close();
				}
				if (imageos != null) {
					imageos.close();
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
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException sqlException) {
				System.out.println("The cause of the exception is ::" + sqlException.getMessage());
			}

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
