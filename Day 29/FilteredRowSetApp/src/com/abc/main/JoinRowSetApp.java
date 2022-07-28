package com.abc.main;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import com.abc.filter.StudentFilter;

public class JoinRowSetApp {

	public static void main(String[] args) throws SQLException, IOException {

		String url = "jdbc:mysql:///abc";
		String user = "root";
		String password = "root123";

		// Using the factory object creating the WebRowSet Object.
		RowSetFactory factory = RowSetProvider.newFactory();
		FilteredRowSet filteredRowSet = factory.createFilteredRowSet();

		// setting the credentials of database
		filteredRowSet.setUrl(url);
		filteredRowSet.setUsername(user);
		filteredRowSet.setPassword(password);

		filteredRowSet.setCommand("select sid,sname,sage from student");

		filteredRowSet.execute();

		filteredRowSet.setFilter(new StudentFilter("sname", "s"));

		while (filteredRowSet.next()) {
			System.out.println(filteredRowSet.getString(1) + "\t" + filteredRowSet.getString(2) + "\t"
					+ filteredRowSet.getString(3));
		}

	}

}
