package kodlama.io.rentACar.core.utilities.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kodlama.io.rentACar.business.concretes.UserManager;
import kodlama.io.rentACar.core.utilities.jwt.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private final UserManager uManager;
	
	private final JwtAuthorizationFilter jwtAuthFilter;
	
	private DataSource dataSource;
	
	
	
	public WebSecurityConfig(UserManager uManager, JwtAuthorizationFilter jwtAuthFilter , DataSource dataSource) {
		
		this.uManager = uManager;
		this.jwtAuthFilter = jwtAuthFilter;
		this.dataSource = dataSource;
	}
	
	
	
	//noOpPasswordEncoder is necessary i guess
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, NoOpPasswordEncoder noOpPasswordEncoder)
            throws Exception {
        /*
    	AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(uManager).passwordEncoder(noOpPasswordEncoder);
        return authenticationManagerBuilder.build();
        */
    	
    	AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    	authenticationManagerBuilder.jdbcAuthentication().passwordEncoder(noOpPasswordEncoder).dataSource(dataSource)
    	.usersByUsernameQuery("Select * from rentacar.users as u where u.username= ?");
    	return authenticationManagerBuilder.build();
    	
    }
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		
		// authorized some requests (Open api ) to access by all users and some request (endpoints that doing processes on db) to acces not by everyone  
	/*	
		http
			.authorizeHttpRequests(
					(request) -> 
							request
								.requestMatchers("/api/users/")
								.anonymous()
								
								.requestMatchers("/api/users/login")
								.anonymous()
								

								
					);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
	*/
		
		//burada kaldık 
		/*
		http.authorizeRequests()
		.requestMatchers("/api/users/auth/login")
		.permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		*/
		
		
		 http.authorizeHttpRequests(auth -> auth
	                .anyRequest().authenticated())
		 			
	            .formLogin(login -> login.defaultSuccessUrl("/swagger-ui/index.html").permitAll())
	            .logout(logout -> logout.permitAll())
	            
	            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	            ;
	         
	       
		 
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
