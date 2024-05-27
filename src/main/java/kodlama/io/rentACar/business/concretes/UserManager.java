package kodlama.io.rentACar.business.concretes;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kodlama.io.rentACar.requests.CreateUserRequest;
import kodlama.io.rentACar.requests.GetUserRequest;
import kodlama.io.rentACar.requests.GetByUsernameRequest;



import kodlama.io.rentACar.business.abstracts.UserService;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.UserRepository;
import kodlama.io.rentACar.entities.concretes.User;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
	
	
	private UserRepository userRepository;
	
	private ModelMapperService modelMapperService;
	
	
	
	@Override
	public void add(CreateUserRequest createUserRequest) {
		// TODO Auto-generated method stub
		
		User user = this.modelMapperService.forRequest()
				.map(createUserRequest, User.class);
		
		this.userRepository.save(user);
		
		
		//return null;
	}

	@Override
	public User get(GetUserRequest getUserRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByUsername(GetByUsernameRequest getByUsernameRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
