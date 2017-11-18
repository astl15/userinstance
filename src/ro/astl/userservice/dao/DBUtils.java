package ro.astl.userservice.dao;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import ro.astl.userservice.utils.UserInstanceEnv;

public class DBUtils {
	public static DataSource getDataSource() {
		Properties properties = UserInstanceEnv.getProperties();
		String driver = properties.getProperty("db.driver");
		String username = properties.getProperty("db.username");
		String password = properties.getProperty("db.password");
		String implementation = properties.getProperty("db.implementation");
		String host = properties.getProperty("db.host");
		String port = properties.getProperty("db.port");
		String dbName = properties.getProperty("db.name");
		String url = implementation  + "://" + host + ":" + port + "/" + dbName;
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
}
