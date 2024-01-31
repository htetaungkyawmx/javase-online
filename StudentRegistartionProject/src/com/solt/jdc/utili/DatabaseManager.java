package com.solt.jdc.utili;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	private static final String URL = 
			"jdbc:mysql://localhost:3306/sturegdb";
	private static final String USER = "root";
	private static final String PASS = "admin";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL,USER,PASS);
	}
}
