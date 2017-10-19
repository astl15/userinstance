package ro.astl.userservice.ws;

import java.util.List;

import javax.jws.WebService;

import ro.astl.userservice.dao.DaoLayer;
import ro.astl.userservice.dao.DaoLayerImpl;
import ro.astl.userservice.model.User;
import ro.astl.userservice.request.UserInstanceIn;
import ro.astl.userservice.response.UserInstanceOut;
@WebService(endpointInterface="ro.astl.userservice.ws.UserInstance",
			serviceName="UserInstanceService")
public class UserInstanceImpl implements UserInstance {
	DaoLayer dao = DaoLayerImpl.getInstance();

	@Override
	public UserInstanceOut getUserbyUsername(UserInstanceIn request) {
		UserInstanceOut output = new UserInstanceOut();
		User userResponse = null;
		List<User> userListResponse = dao.getUserbyUsername(request.getUsername());
		if(!userListResponse.isEmpty()) {
			userResponse = userListResponse.get(0);
		}
		if(userResponse!=null && userResponse.getUsername()!=null) {
			output.setResponseCode("SUCCESS");
			output.setId(userResponse.getId());
			output.setUsername(userResponse.getUsername());
			output.setPassword(userResponse.getPassword());
		}else {
			output.setResponseCode("NODATA");
		}
		return output;
	}

	@Override
	public UserInstanceOut createUser(UserInstanceIn request) {
		UserInstanceOut output = new UserInstanceOut();
		boolean isExecuted = dao.createUser(request.getUsername(), request.getPassword());
		if(isExecuted){
			output.setResponseCode("SUCCES");
		}else {
			output.setResponseCode("FAILURE");
		}
		return output;
	}

}
