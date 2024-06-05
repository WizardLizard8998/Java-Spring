package kodlama.io.rentACar.business.concretes;


import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kodlama.io.rentACar.requests.CreateUserRequest;
import kodlama.io.rentACar.responses.GetByUsernameUserResponse;
import kodlama.io.rentACar.responses.GetUserResponse;
import kodlama.io.rentACar.business.abstracts.UserService;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.UserRepository;
import kodlama.io.rentACar.entities.concretes.Users;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

//multiple inheritance is not allowed in java  but (WTF) however it can implement multiple interfaces
public class UserManager implements UserService , UserDetailsService{
	
	
	private UserRepository userRepository;
	
	private ModelMapperService modelMapperService;
	
	
	
	@Override
	public void add(CreateUserRequest createUserRequest) {
		// TODO Auto-generated method stub
		
		Users users = this.modelMapperService.forRequest()
				.map(createUserRequest, Users.class);
		
		this.userRepository.save(users);
		
		
		//return null;
	}

	@Override
	public GetUserResponse get(String username , String password) {
		// TODO Auto-generated method stub

		
			Users userRes = this.userRepository.FindByUsernamePassword(username,password);
			
			GetUserResponse userResponse = this.modelMapperService.forResponse().map(userRes, GetUserResponse.class);
		
		
		return userResponse;
		
	}

	@Override
	public GetByUsernameUserResponse getByUsername(String username) {
		// TODO Auto-generated method stub
		
		Users users = this.userRepository.FindByUsername(username);
		
		GetByUsernameUserResponse resp = this.modelMapperService.forResponse().map(users, GetByUsernameUserResponse.class);
		
		return resp;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users users = this.userRepository.FindByUsername(username);
		
		//roles given here 
		List<String> roles = new ArrayList<>();
		
		roles.add("Admin");
								
								//there is already a imported package user 
		UserDetails uDetails = org.springframework.security.core.userdetails.User
								.builder()
								.username(users.getUsername())
								.password(users.getPassword())
								.roles(roles.get(0))
								.build();
		
		
		return uDetails;
	}

	
	@Override
	public Users getUserForAuth(String username, String password) {
		// TODO Auto-generated method stub
		
		Users users = this.userRepository.FindByUsernamePassword(username, password);
		
		return users;
	}
	
	
	
	
	
}
