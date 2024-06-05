package kodlama.io.rentACar.webApi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import kodlama.io.rentACar.business.abstracts.UserService;
import kodlama.io.rentACar.core.utilities.jwt.JwtUtil;
import kodlama.io.rentACar.entities.concretes.Users;
import kodlama.io.rentACar.requests.CreateUserRequest;
import kodlama.io.rentACar.requests.LoginUserRequest;
import kodlama.io.rentACar.responses.GetByUsernameUserResponse;
import kodlama.io.rentACar.responses.GetUserResponse;
import kodlama.io.rentACar.responses.LoginResponseAuth;


@RestController 
@RequestMapping("/api/users")
public class UserController {

	private UserService userService; 
	
	private JwtUtil jwtUtil;
	
	
	AuthenticationManager authManager;
	
	@Autowired
	public UserController(UserService userService , JwtUtil jwtUtil , AuthenticationManager authManager) {
		super();
		this.userService = userService;
		this.jwtUtil = jwtUtil;
		this.authManager = authManager;
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
	public LoginResponseAuth login(@RequestBody LoginUserRequest logReq) {
		
		
		/**  IMPORTANT*/
		
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(logReq.getUsername(), logReq.getPassword()));
		
		Users users = this.userService.getUserForAuth(logReq.getUsername(), logReq.getPassword());
		
		String token = jwtUtil.createToken(users);
		
		LoginResponseAuth resp = new LoginResponseAuth(logReq.getUsername(), token);
		
		
		return resp;
		
	}
	
	
	
	
	
}
