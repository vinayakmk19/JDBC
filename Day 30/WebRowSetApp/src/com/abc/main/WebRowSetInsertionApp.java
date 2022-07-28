package com.abc.main;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class WebRowSetInsertionApp {

	public static void main(String[] args) throws SQLException, IOException, InterruptedException {

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "system";
		String password = "root123";

		// Use RowSetFactory to create CachedRowSet Object
		RowSetFactory factory = RowSetProvider.newFactory();
		WebRowSet webRowSet = factory.createWebRowSet();

		// setting credentials through setter method
		webRowSet.setUrl(url);
		webRowSet.setUsername(user);
		webRowSet.setPassword(password);

		// setting the command for execution
		webRowSet.setCommand("select sid,sname,sage,saddress from student");
		webRowSet.execute();

		// Read the xml data from the xml file and perform the operation
		FileReader reader = new FileReader("input.xml");
		webRowSet.readXml(reader);//offline mode operation

		//To make the changes to happen in database
		webRowSet.acceptChanges();
		System.out.println("Insertion operation done through XML!!!!");

	}

}
