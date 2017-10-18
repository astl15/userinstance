package ro.astl.userservice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import ro.astl.userservice.model.User;

public class DaoLayerImpl implements DaoLayer {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "wltmngr";
	private static DaoLayer instance = DaoLayerImpl.getInstance();
	private final DataSource dataSource = DaoLayerImpl.getDataSource();
	

	private DaoLayerImpl(){	
	}
	
	@Override
	public boolean createUser(String username, String password) {
		boolean isExecuted = false;
		try(Connection conn = dataSource.getConnection();
			PreparedStatement stmnt = DaoLayerImpl.prepareStatement("createUser", conn, username, password)){
			int affectedRows = stmnt.executeUpdate();
			if(affectedRows>0) {
				isExecuted = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isExecuted;
		
	}

	@Override
	public User getUserbyUsername(String Username) {
		User user = new User();
		try(Connection conn = dataSource.getConnection();
			PreparedStatement stmnt = DaoLayerImpl.prepareStatement("getUserbyUsername", conn, Username);
			ResultSet rs = stmnt.executeQuery()){
			while(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static final DaoLayer getInstance(){
		if(instance == null){
			synchronized(DaoLayerImpl.class){
				if(instance == null){
					instance = new DaoLayerImpl();
				}
			}
		}
		return instance;
	}
	
	private static final DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/wltmngr");
		dataSource.setUsername("root");
		dataSource.setPassword("wltmngr");
		return dataSource;
	}
	
	/*DO NOT USE THIS IN PROD - METHOD JUST FOR OCP PREP*/
	private static final Connection getConnection() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);	
		return conn;
	}
	
	private static PreparedStatement prepareStatement(String operation, Connection conn, String... paramsSQL) throws SQLException {
		PreparedStatement stmnt = null;
		String sql = "";
		if(paramsSQL.length<1) {
			return stmnt;
		}else {
			switch(operation) {
				case "getUserbyUsername":
					sql = "SELECT * FROM wltmngr.users WHERE username = ?";
					stmnt = conn.prepareStatement(sql);
					stmnt.setString(1, paramsSQL[0]);
					break;
				case "createUser":
					sql = "INSERT INTO wltmngr.users(username,password)VALUES(?,?)";
					stmnt = conn.prepareStatement(sql);
					stmnt.setString(1, paramsSQL[0]);
					stmnt.setString(2, paramsSQL[1]);
					break;
			}
			return stmnt;
		}
	}

}
