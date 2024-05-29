package kodlama.io.rentACar.business.abstracts;




import org.springframework.security.core.userdetails.UserDetails;

import kodlama.io.rentACar.requests.CreateUserRequest;
//import kodlama.io.rentACar.requests.GetByUsernameRequest;
import kodlama.io.rentACar.responses.GetByUsernameUserResponse;
//import kodlama.io.rentACar.requests.GetUserRequest;
//import kodlama.io.rentACar.requests.GetUserRequest;
import kodlama.io.rentACar.responses.GetUserResponse;



public interface UserService {

	public void add(CreateUserRequest createUserRequest);
	
	public GetUserResponse get(String username, String password);
	
	public GetByUsernameUserResponse getByUsername(String username);
	
	public UserDetails getUserForAuth(String username ,String password);

}
