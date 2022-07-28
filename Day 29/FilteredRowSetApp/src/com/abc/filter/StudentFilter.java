package com.abc.filter;

import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.Predicate;

public class StudentFilter implements Predicate {

	private String colName;
	private String colCondition;

	public StudentFilter(String colName, String colCondition) {
		this.colName = colName;
		this.colCondition = colCondition;
	}

	@Override
	public boolean evaluate(RowSet rowSet) {
		// Called everytime where rs.next() is used in our main logic

		try {

			String colValue = rowSet.getString(colName);

			/*
			 * if (colValue.startsWith(colCondition)) { return true; } else { return false;
			 * }
			 */

			// return colValue.startsWith(colCondition) ? true : false;

			return colValue.startsWith(colCondition);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean evaluate(Object value, int column) throws SQLException {
		// Called everytime when insert,update,delete is applied on rowSet Object using
		// colIndex.
		return false;
	}

	@Override
	public boolean evaluate(Object value, String columnName) throws SQLException {
		// Called everytime when insert,update,delete is applied on rowSet Object using
		// colName.
		return false;
	}

}
