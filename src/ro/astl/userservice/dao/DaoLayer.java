package ro.astl.userservice.dao;

import java.util.List;

import ro.astl.userservice.model.User;

public interface DaoLayer {
	public boolean registerUser(String username, String password, int role);
	public List<User> getUserbyUsername(String Username);
	public User authenticateUser(String username, String password);
}
