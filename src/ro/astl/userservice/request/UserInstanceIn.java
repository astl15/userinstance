package ro.astl.userservice.request;

public class UserInstanceIn {
	private ServiceContext conxtext;
	private int id;
	private String username;
	private String password;
	
	public UserInstanceIn(){
	}
	
	public UserInstanceIn(ServiceContext context, int id, String username, String password){
		this.conxtext = context;
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public ServiceContext getConxtext() {
		return conxtext;
	}

	public void setConxtext(ServiceContext conxtext) {
		this.conxtext = conxtext;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
