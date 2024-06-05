package kodlama.io.rentACar.core.utilities.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import kodlama.io.rentACar.business.concretes.UserManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private final UserManager uManager;
	
	
	
	
	public WebSecurityConfig(UserManager uManager) {
		
		this.uManager = uManager;
	}
	
	
	
	//noOpPasswordEncoder is necessary i guess
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, NoOpPasswordEncoder noOpPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(uManager).passwordEncoder(noOpPasswordEncoder);
        return authenticationManagerBuilder.build();
    }
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		
		// authorized some requests (Open api ) to access by all users and some request (endpoints that doing processes on db) to acces not by everyone  
		http
			.authorizeHttpRequests(
					(request) -> 
							request
								.requestMatchers("/swagger-ui/**")
								.anonymous()
								.requestMatchers("/v3/api-docs/**")
								.anonymous()
								
								.requestMatchers("/api/**")
								.authenticated()
								
								/**/
								
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
	
	// I GUESS
    @SuppressWarnings("deprecation")
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
	
	
	
	
}
