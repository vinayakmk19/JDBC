package com.abc.main;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class CachedRowSetUpdataApp {

	public static void main(String[] args) throws SQLException, IOException, InterruptedException {

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "system";
		String password = "root123";

		// Use RowSetFactory to create CachedRowSet Object
		RowSetFactory factory = RowSetProvider.newFactory();
		CachedRowSet cachedRowSet = factory.createCachedRowSet();

		// setting credentials through setter method
		cachedRowSet.setUrl(url);
		cachedRowSet.setUsername(user);
		cachedRowSet.setPassword(password);

		// setting the command for execution
		cachedRowSet.setCommand("select sid,sname,sage,saddress from student");
		cachedRowSet.execute();

		System.out.println("SID\tSNAME\tSAGE\tSADDRESS");

		while (cachedRowSet.next()) {
			System.out.println(cachedRowSet.getInt(1) + "\t" + cachedRowSet.getString(2) + "\t" + cachedRowSet.getInt(3)
					+ "\t" + cachedRowSet.getString(4));
		}

		cachedRowSet.absolute(4);
		System.out.println("------------------------------------------");
		System.out.println(cachedRowSet.getInt(1) + "\t" + cachedRowSet.getString(2) + "\t" + cachedRowSet.getInt(3)
				+ "\t" + cachedRowSet.getString(4));

		System.out.println("=======Datbase S/w off mode======");
		System.in.read();

		cachedRowSet.updateString(4, "Barbodas");
		cachedRowSet.updateRow(); // offline manipulation for Db table

		System.out.println("=====Datbase S/w switching on===========");
		Thread.sleep(30000);

		cachedRowSet.acceptChanges();// reflection will happen automatically to the db even if changes happened in
										// offline mode

		System.out.println("After ofline manipulation data is ::");
		System.out.println(cachedRowSet.getInt(1) + "\t" + cachedRowSet.getString(2) + "\t" + cachedRowSet.getInt(3)
				+ "\t" + cachedRowSet.getString(4));

	}

}
