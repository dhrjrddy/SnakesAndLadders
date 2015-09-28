package org.snakesandladdersgame;

import java.sql.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DBConnection {

	private static DBConnection dbConnection;
	private BasicDataSource ds;
	private static final String dbDriverClass = "dbDriverClass";
	private static final String dbUser = "dbUser";
	private static final String dbPassword = "dbPassword";
	private static final String dataBaseURL = "databaseURL";

	private DBConnection() throws SQLException {
		Properties prop = new Properties();
		String fileName = "dbproperties.config";
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream(fileName);
		try {
			prop.load(input);
			ds = new BasicDataSource();
			ds.setDriverClassName(prop.getProperty(dbDriverClass));
			ds.setUsername(prop.getProperty(dbUser));
			ds.setPassword(prop.getProperty(dbPassword));
			ds.setUrl(prop.getProperty(dataBaseURL));
			input.close();
		} catch (IOException e) {
			System.out.println("Specified file doesn't exist");
		}
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
