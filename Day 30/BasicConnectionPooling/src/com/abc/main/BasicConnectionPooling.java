package com.abc.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class BasicConnectionPooling {

	public static void main(String[] args) throws SQLException {

		// Creating a pool of Connection Objects
		MysqlDataSource dataSource = new MysqlDataSource();

		//Set the url,username,password for dataSource Object
		dataSource.setUrl("jdbc:mysql:///abc");
		dataSource.setUser("root");
		dataSource.setPassword("root123");

		//using dataSource Object get the logical connection to DB
		Connection connection = dataSource.getConnection();
		
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from student");

		System.out.println("SID\tSNAME\tSAGE");
		System.out.println("------------------------------------------");
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
		}
		
		//Since it is a logical connection from Connection pool,connection will not be destroyed
		//it will be sent back to connection pool for further usage.
		connection.close();

	}

}
