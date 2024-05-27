package kodlama.io.rentACar.business.abstracts;

import java.util.*;

import kodlama.io.rentACar.entities.concretes.User;
import kodlama.io.rentACar.requests.CreateUserRequest;
import kodlama.io.rentACar.requests.GetByUsernameRequest;
import kodlama.io.rentACar.requests.GetUserRequest;



public interface UserService {

	public void add(CreateUserRequest createUserRequest);
	
	public User get(GetUserRequest getUserRequest);
	
	public User getByUsername(GetByUsernameRequest getByUsernameRequest);

}
