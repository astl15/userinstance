package ro.astl.userservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import ro.astl.userservice.model.User;

public class DaoLayerImpl implements DaoLayer {
	
	private static DaoLayer instance = DaoLayerImpl.getInstance();
	private final DataSource dataSource = DBUtils.getDataSource();
	

	private DaoLayerImpl(){	
	}
	
	@Override
	public boolean registerUser(String username, String password, int role) {
		boolean isExecuted = false;
		try(Connection conn = dataSource.getConnection();
			PreparedStatement stmnt = DaoLayerImpl.prepareRegisterUserStatement(conn, username, password, role)){
			int affectedRows = stmnt.executeUpdate();
			if(affectedRows>0) {
				isExecuted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExecuted;
		
	}

	@Override
	public List<User> getUserbyUsername(String Username) {
		List<User> userList = new ArrayList<User>();
		try(Connection conn = dataSource.getConnection();
			PreparedStatement stmnt = DaoLayerImpl.prepareGetUsersStatement(conn, Username);
			ResultSet rs = stmnt.executeQuery()){
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
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
	
	private static PreparedStatement prepareRegisterUserStatement(Connection conn, String username, String password, int role) throws SQLException {
		PreparedStatement stmnt = null;
		String sql = "INSERT INTO wltmngr.users(username,password,role)VALUES(?,?,?)";
		stmnt = conn.prepareStatement(sql);
		stmnt.setString(1, username);
		stmnt.setString(2, password);
		stmnt.setInt(3, role);
		return stmnt;
	}
	
	private static PreparedStatement prepareGetUsersStatement(Connection conn, String username) throws SQLException{
		PreparedStatement stmnt = null;
		String sql = "SELECT u.username, u.password, r.name as role FROM users u INNER JOIN user_roles r ON u.role = r.id WHERE u.username = ?";
		stmnt = conn.prepareStatement(sql);
		stmnt.setString(1, username);
		return stmnt;	
	}

}
