package com.abc.main;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JoinRowSetApp {

	public static void main(String[] args) throws SQLException, IOException {

		String url = "jdbc:mysql:///abc";
		String user = "root";
		String password = "root123";

		// Using the factory object creating the WebRowSet Object.
		RowSetFactory factory = RowSetProvider.newFactory();
		JdbcRowSet jdbcRowSet1 = factory.createJdbcRowSet();

		jdbcRowSet1.setUrl(url);
		jdbcRowSet1.setUsername(user);
		jdbcRowSet1.setPassword(password);
		jdbcRowSet1.setCommand("select eno,ename,eaddress,eage,dno from employee");
		jdbcRowSet1.setMatchColumn(5);// matching dno
		jdbcRowSet1.execute();

		JdbcRowSet jdbcRowSet2 = factory.createJdbcRowSet();
		jdbcRowSet2.setUrl(url);
		jdbcRowSet2.setUsername(user);
		jdbcRowSet2.setPassword(password);
		jdbcRowSet2.setCommand("select dno,dname,dloc from department");
		jdbcRowSet2.setMatchColumn(1);
		jdbcRowSet2.execute();
		
		
		JoinRowSet joinRowSet = factory.createJoinRowSet();
		joinRowSet.addRowSet(jdbcRowSet1);
		joinRowSet.addRowSet(jdbcRowSet2);

		System.out.println("Accessing the recording by joining the tables");
		
		while (joinRowSet.next()) {
			System.out.println(joinRowSet.getString(1) + "\t" + joinRowSet.getString(2) + "\t" + joinRowSet.getString(3)
					+ "\t" + joinRowSet.getString(4) + "\t" + joinRowSet.getString(5) + "\t\t" + joinRowSet.getString(6)
					+ "\t" + joinRowSet.getString(7));
		}
		

	}

}
