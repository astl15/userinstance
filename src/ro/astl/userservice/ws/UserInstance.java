package ro.astl.userservice.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

import ro.astl.userservice.request.UserInstanceIn;
import ro.astl.userservice.response.UserInstanceOut;

@WebService(name="UserInstanceService")
public interface UserInstance {
	public UserInstanceOut getUserbyUsername(@WebParam(name="request")UserInstanceIn request);
	public UserInstanceOut createUser(@WebParam(name="request")UserInstanceIn request);
}
