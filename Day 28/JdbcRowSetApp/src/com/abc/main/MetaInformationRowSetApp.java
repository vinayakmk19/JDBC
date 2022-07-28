package com.abc.main;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class MetaInformationRowSetApp {

	public static void main(String[] args) throws SQLException {

		// Creating of RowSet Objects
		RowSetFactory factory = RowSetProvider.newFactory();

		JdbcRowSet jdbcRowSet = factory.createJdbcRowSet();
		CachedRowSet cachedRowSet = factory.createCachedRowSet();
		WebRowSet webRowSet = factory.createWebRowSet();
		JoinRowSet joinRowSet = factory.createJoinRowSet();
		FilteredRowSet filteredRowSet = factory.createFilteredRowSet();

		System.out.println("JDBCRowSet Implementation Class is ::" + jdbcRowSet.getClass().getName());
		System.out.println("CachedRowSet Implementation Class is :: " + cachedRowSet.getClass().getName());
		System.out.println("WebRowSet Implemenation Class is :: " + webRowSet.getClass().getName());
		System.out.println("JoinRowSet Implementation Class is : " + joinRowSet.getClass().getName());
		System.out.println("FilteredRowSet Implemenation Class is :: " + filteredRowSet.getClass().getName());

	}

}
