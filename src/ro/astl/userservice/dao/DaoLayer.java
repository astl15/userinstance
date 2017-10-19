package ro.astl.userservice.dao;

import java.util.List;

import ro.astl.userservice.model.User;

public interface DaoLayer {
	public boolean createUser(String username, String password);
	public List<User> getUserbyUsername(String Username);
}
