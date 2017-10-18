package ro.astl.userservice.dao;

import ro.astl.userservice.model.User;

public interface DaoLayer {
	public boolean createUser(String username, String password);
	public User getUserbyUsername(String Username);
}
