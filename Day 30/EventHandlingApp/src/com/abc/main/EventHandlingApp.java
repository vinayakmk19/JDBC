package com.abc.main;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import com.abc.listener.StudentListener;

public class EventHandlingApp {

	public static void main(String[] args) throws SQLException, IOException, InterruptedException {

		String url = "jdbc:mysql:///abc";
		String user = "root";
		String password = "root123";

		// Use RowSetFactory to create CachedRowSet Object
		RowSetFactory factory = RowSetProvider.newFactory();
		JdbcRowSet jdbcRowSet = factory.createJdbcRowSet();

		jdbcRowSet.setUrl(url);
		jdbcRowSet.setUsername(user);
		jdbcRowSet.setPassword(password);

		jdbcRowSet.setCommand("select sid,sname,sage from student");
		StudentListener listener = new StudentListener();
		jdbcRowSet.addRowSetListener(listener);
		
		jdbcRowSet.execute();

		
		
		System.out.println("SID\tSNAME\tSAGE");
		System.out.println("------------------------------------------------------------");
		while (jdbcRowSet.next()) {

			System.out
					.println(jdbcRowSet.getString(1) + "\t" + jdbcRowSet.getString(2) + "\t" + jdbcRowSet.getString(3));
			System.out.println("----------------------------------------------------");
		}

		jdbcRowSet.absolute(4);
		jdbcRowSet.updateInt(3, 49);
		jdbcRowSet.updateRow();
		System.out.println("Updated Succesfully!!!!");

	}

}
