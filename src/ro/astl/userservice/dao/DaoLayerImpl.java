package ro.astl.userservice.dao;

import ro.astl.userservice.model.User;

public class DaoLayerImpl implements DaoLayer {
	private static DaoLayer instance = DaoLayerImpl.getInstance();

	private DaoLayerImpl(){	
	}
	
	@Override
	public void createUser(String username, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserbyUsername(String Username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static final DaoLayer getInstance(){
		if(instance == null){
			synchronized(DaoLayerImpl.class){
				if(instance == null){
					instance = new DaoLayerImpl();
				}
			}
		}
		return instance;
	}

}
