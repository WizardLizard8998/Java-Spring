package kodlama.io.rentACar.core.utilities.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		
		// authorized some requests (Open api ) to access by all users and some request (endpoints that doing processes on db) to acces not by everyone  
		http
			.authorizeHttpRequests(
					(request) -> 
							request
								.requestMatchers("/api/**")
								.authenticated()
								
								.requestMatchers("/swagger-ui/**")
								.anonymous()
								.requestMatchers("/v3/api-docs/**")
								.anonymous()
					);
			
		
		
			
		
		return http.build();
		
		
	}
	
	
	/*
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user = User.withDefaultPasswordEncoder()
								.username("admin")
								.password("admin")
								.roles("Admin")
								.build();
		
		return new InMemoryUserDetailsManager(user);
		
		
	}
	*/
	
	
	
	
	
}
