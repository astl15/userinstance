package ro.astl.userservice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import ro.astl.userservice.model.User;

public class DaoLayerImpl implements DaoLayer {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "wltmngr";
	
	private static DaoLayer instance = DaoLayerImpl.getInstance();
	private DataSource dataSource;
	

	private DaoLayerImpl(){	
	}
	
	@Override
	public void createUser(String username, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserbyUsername(String Username) {
		User user = new User();
		try(Connection conn = DaoLayerImpl.getConnection();
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
	
	private static final DataSource getMySqlDataSource() {
		return null;
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
		if(paramsSQL.length<1) {
			return stmnt;
		}else {
			switch(operation) {
				case "getUserbyUsername":
					String sql = "SELECT * FROM wltmngr.users WHERE username = ?";
					stmnt = conn.prepareStatement(sql);
					stmnt.setString(1, paramsSQL[0]);
					break;
			}
			return stmnt;
		}
	}

}
