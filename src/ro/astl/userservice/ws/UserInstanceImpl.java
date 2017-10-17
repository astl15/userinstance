package ro.astl.userservice.ws;

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
		User userResponse = dao.getUserbyUsername(request.getUsername());
		if(userResponse.getUsername()!=null) {
			output.setResponseCode("SUCCESS");
			output.setId(userResponse.getId());
			output.setUsername(userResponse.getUsername());
			output.setPassword(userResponse.getPassword());
		}else {
			output.setResponseCode("NODATA");
		}
		return output;
	}

}
