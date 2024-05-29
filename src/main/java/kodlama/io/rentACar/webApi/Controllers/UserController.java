package kodlama.io.rentACar.webApi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import kodlama.io.rentACar.business.abstracts.UserService;
import kodlama.io.rentACar.core.utilities.token.JWTUtil;
import kodlama.io.rentACar.requests.AuthRequest;
import kodlama.io.rentACar.requests.CreateUserRequest;

import kodlama.io.rentACar.responses.GetByUsernameUserResponse;
import kodlama.io.rentACar.responses.GetUserResponse;


@RestController 
@RequestMapping("/api/users")
public class UserController {

	private UserService userService; 
	
	
	
	/*
	 * injected these two in UserController 
	 */
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager aManager; 
	
	
	
	
	
	
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
	
	
	@PostMapping("/login")
	public String CreateToken(@RequestBody AuthRequest aRequest) throws Exception {
		
		String token="";
		
		try {
			final UserDetails userDetails = this.userService.getUserForAuth(aRequest.getUsername(), aRequest.getPassword());
			
			token = jwtUtil.generateToken(userDetails); 
			
			return token ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return token;
	}
	
	
	
	
	
	
}
