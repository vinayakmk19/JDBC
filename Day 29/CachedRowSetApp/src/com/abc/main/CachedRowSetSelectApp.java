package com.abc.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class CachedRowSetSelectApp {

	public static void main(String[] args) throws SQLException, IOException {

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "system";
		String password = "root123";
		Connection connection = DriverManager.getConnection(url, user, password);

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select sid,sname,sage,saddress from student");

		// Use RowSetFactory to create CachedRowSet Object
		RowSetFactory factory = RowSetProvider.newFactory();
		CachedRowSet cachedRowSet = factory.createCachedRowSet();

		// Before closing the connection copy resultSet Object to CachedRowSet object
		cachedRowSet.populate(resultSet);

		connection.close();

		System.out.println("SID\tSNAME\tSAGE\tSADDRESS");

		while (cachedRowSet.next()) {
			System.out.println(cachedRowSet.getInt(1) + "\t" + cachedRowSet.getString(2) + "\t" + cachedRowSet.getInt(3)
					+ "\t" + cachedRowSet.getString(4));
		}

	}

}
