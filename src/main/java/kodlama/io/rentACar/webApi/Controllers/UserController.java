package kodlama.io.rentACar.webApi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import kodlama.io.rentACar.business.abstracts.UserService;
import kodlama.io.rentACar.requests.CreateUserRequest;

import kodlama.io.rentACar.responses.GetByUsernameUserResponse;
import kodlama.io.rentACar.responses.GetUserResponse;


@RestController 
@RequestMapping("/api/users")
public class UserController {

	private UserService userService; 
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody CreateUserRequest createUserRequest) {
		
		 this.userService.add(createUserRequest);
		
	}
	
	
	@GetMapping()
	@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
	public GetUserResponse get( @RequestParam String username, @RequestParam String password) {
		return this.userService.get(username,password);
	}
	
	
	@GetMapping("/{username}")
	public GetByUsernameUserResponse getByUsername(@PathVariable String username) {
		return this.userService.getByUsername(username);
	}
	
	
	
	
	
}
