package com.abc.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariConnectionPooling {

	public static void main(String[] args) throws SQLException {

		HikariConfig config = new HikariConfig();

		config.setJdbcUrl("jdbc:mysql:///abc");
		config.setUsername("root");
		config.setPassword("root123");
		config.setMaximumPoolSize(20);

		HikariDataSource dataSource = new HikariDataSource(config);

		Connection connection = dataSource.getConnection();

		System.out.println("The implementation class Name is :: " + connection.getClass().getName());

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select sid,sname,sage from student");

		System.out.println("SID\t SNAME\t SAGE");
		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
		}
		dataSource.close();
		connection.close();

	}

}
