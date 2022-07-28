package com.abc.main;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcRowSetSelectApp {

	public static void main(String[] args) throws SQLException, IOException {

		// Using RowSetFactory to create JdbcRowSet Object
		RowSetFactory factory = RowSetProvider.newFactory();
		JdbcRowSet jdbcRowSet = factory.createJdbcRowSet();

		// Setting the db credenitals through setter methods
		jdbcRowSet.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		jdbcRowSet.setUsername("system");
		jdbcRowSet.setPassword("root123");

		// Setting the query for execution
		jdbcRowSet.setCommand("select sid,sname,sage,saddress from student");

		// Executing the Query
		jdbcRowSet.execute();

		System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
		// process the RowSet data
		while (jdbcRowSet.next()) {
			System.out.println(jdbcRowSet.getInt(1) + "\t" + jdbcRowSet.getString(2) + "\t" + jdbcRowSet.getInt(3)
					+ "\t" + jdbcRowSet.getString(4));
		}

		jdbcRowSet.absolute(3);
		System.out.println("......................................");
		System.out.println(jdbcRowSet.getInt(1) + "\t" + jdbcRowSet.getString(2) + "\t" + jdbcRowSet.getInt(3) + "\t"
				+ jdbcRowSet.getString(4));

		System.out.println();

		jdbcRowSet.absolute(-3);

		System.out.println("......................................");
		System.out.println(jdbcRowSet.getInt(1) + "\t" + jdbcRowSet.getString(2) + "\t" + jdbcRowSet.getInt(3) + "\t"
				+ jdbcRowSet.getString(4));

		jdbcRowSet.absolute(3);
		jdbcRowSet.updateString(4, "RCB");
		jdbcRowSet.updateRow();

		System.out.println("......................................");
		System.out.println(jdbcRowSet.getInt(1) + "\t" + jdbcRowSet.getString(2) + "\t" + jdbcRowSet.getInt(3) + "\t"
				+ jdbcRowSet.getString(4));

		jdbcRowSet.beforeFirst();
		System.out.println("Application is Paused.. Make changes in the database!!!");
		System.in.read();
		while (jdbcRowSet.next()) {
			jdbcRowSet.refreshRow();
			System.out.println(jdbcRowSet.getInt(1) + "\t" + jdbcRowSet.getString(2) + "\t" + jdbcRowSet.getInt(3)
					+ "\t" + jdbcRowSet.getString(4));
		}

	}

}
