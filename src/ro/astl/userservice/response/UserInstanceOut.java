package ro.astl.userservice.response;

public class UserInstanceOut {
	private String responseCode;
	private int id;
	private String username;
	private String password;
	
	public UserInstanceOut(){
	}
	
	public UserInstanceOut(String responseCode, int id, String username, String password){
		this.responseCode = responseCode;
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
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
