package org.snakesandladdersgame;

import java.sql.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

public class DbConnection {

	private static DbConnection dbConnection;
	private BasicDataSource dataSource;
	private static final String DRIVER_CLASS = "db_driver";
	private static final String USER = "db_user";
	private static final String PASSWORD = "db_password";
	private static final String URL = "db_url";
	private static Logger log = Logger.getLogger(DbInventoryImp.class);

	private DbConnection() throws IOException  {
		Properties prop = new Properties();
		String fileName = "dbproperties.config";
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream(fileName);
		try {
			prop.load(input);
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(prop.getProperty(DRIVER_CLASS));
			dataSource.setUsername(prop.getProperty(USER));
			dataSource.setPassword(prop.getProperty(PASSWORD));
			dataSource.setUrl(prop.getProperty(URL));
			input.close();
		} catch (IOException e) {
			log.error("Failed to extract data from file: " + e);
			throw new IOException(e);
		}
		
	}

	public static DbConnection getInstance() throws IOException  {
		if (dbConnection == null) {
			dbConnection = new DbConnection();
			return dbConnection;
		} else {
			return dbConnection;
		}
	}

	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
}
