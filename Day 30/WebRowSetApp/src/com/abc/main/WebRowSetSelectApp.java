package com.abc.main;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class WebRowSetSelectApp {

	public static void main(String[] args) throws SQLException, IOException {

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "system";
		String password = "root123";

		// Using the factory object creating the WebRowSet Object.
		RowSetFactory factory = RowSetProvider.newFactory();
		WebRowSet webRowSet = factory.createWebRowSet();

		// Setting the values of dbCredentials
		webRowSet.setUrl(url);
		webRowSet.setUsername(user);
		webRowSet.setPassword(password);

		// Setting the sqlQuery
		webRowSet.setCommand("select sid,sname,sage,saddress from student");

		// Setting the no of Rows to be retrieved during the execution
		webRowSet.setMaxRows(3);

		// Executing the Query
		webRowSet.execute();

		// Creating the xml file for writing operation
		FileWriter writer = new FileWriter("sample.xml");

		// Using webRowSet write the data into XML file
		webRowSet.writeXml(writer);

		System.out.println("Writing the data to console");
		webRowSet.writeXml(System.out);

	}

}
