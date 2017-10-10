package ro.astl.userservice.ws;

import javax.jws.WebService;

import ro.astl.userservice.request.UserInstanceIn;
import ro.astl.userservice.response.UserInstanceOut;
@WebService(endpointInterface="ro.astl.userservice.ws.UserInstance",
			serviceName="UserInstanceService")
public class UserInstanceImpl implements UserInstance {

	@Override
	public UserInstanceOut getUserbyUsername(UserInstanceIn request) {
		UserInstanceOut output = new UserInstanceOut();
		output.setResponseCode("SUCCESS");
		output.setId(1);
		output.setUsername("andrei");
		output.setPassword("test1234");
		
		return output;
	}

}
