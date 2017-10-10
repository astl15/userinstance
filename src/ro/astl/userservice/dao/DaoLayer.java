package ro.astl.userservice.dao;

import ro.astl.userservice.model.User;

public interface DaoLayer {
	public void createUser(String username, String password);
	public User getUserbyUsername(String Username);
}
