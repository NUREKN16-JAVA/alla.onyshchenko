package com.nixsolutions.usermanagement.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.nixsolutions.usermanagement.User;

public class UserTableModel extends AbstractTableModel {

	private static final String[] COLUMN_NAMES = {"ID", "Имя", "Фамилия"};
	private static final Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};	
	private List users = null;
	
	public UserTableModel(Collection users) {
		this.users = new ArrayList(users);
	}
	
	@Override
	public int getRowCount() {
		
		return users.size();
	}

	@Override
	public int getColumnCount() {
		
		return COLUMN_NAMES.length;
	}
	
	

	public Class getColumnClass(int columnIndex) {
		
		return COLUMN_CLASSES[columnIndex];
	}

	public String getColumnName(int column) {
		
		return COLUMN_NAMES[column];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch (columnIndex) {
		case 0;
			return user.getId();
		case 1;
			return user.getFirstname();
		case 2;
			return user.getLastname();
		}
		return null;
	}
	
	public void addUsers(Collection users) {
		this.users.addAll(users);
	}

	public void clearUsers() {
		this.users = new ArrayList();
	}
	
}
