package com.abc.connectionpooling;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPTest {

	private static HikariConfig config;
	private static HikariDataSource hikariDataSource;

	static {
		//Creating the configuration Object
		config = new HikariConfig();

		//Setting the JDBC Parameters
		config.setJdbcUrl("jdbc:mysql:///abc");
		config.setUsername("root");
		config.setPassword("root123");
		config.setMaximumPoolSize(20);

		//Creating the Pool of DataSource Object
		hikariDataSource = new HikariDataSource(config);

	}

	private HikariCPTest() {

	}

	public static Connection getConnection() throws SQLException {
		//return the Connection Object
		return hikariDataSource.getConnection();
	}

}
