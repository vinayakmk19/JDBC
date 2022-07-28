package com.abc.listener;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;

public class StudentListener implements RowSetListener {

	@Override
	public void rowSetChanged(RowSetEvent event) {
		System.out.println("RowSet Object is used!!!!");
	}

	@Override
	public void rowChanged(RowSetEvent event) {
		System.out.println("Row is changed by the user!!!");

	}

	@Override
	public void cursorMoved(RowSetEvent event) {
		System.out.println("Cursor is moved in the rowSet Object");

	}

}
