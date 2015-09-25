package org.snakesandladdersgame;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DBConnection {

	private static DBConnection dbConnection;
	private BasicDataSource ds;

	private DBConnection() throws SQLException {
		ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("dheeraj");
		ds.setUrl("jdbc:mysql://localhost/snakesandladders");

	}

	public static DBConnection getInstance() throws SQLException {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
			return dbConnection;
		} else {
			return dbConnection;
		}
	}

	public Connection getConnection() throws SQLException {
		return this.ds.getConnection();
	}
}
