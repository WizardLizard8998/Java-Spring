package kodlama.io.rentACar.core.utilities.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties.Embedded;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.activation.DataSource;
import kodlama.io.rentACar.core.utilities.token.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Autowired
	private JwtFilter jwtFilter;
	
	/*
	 * configure the user types and users 
	 */
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		
		
		//creating a user for webSec login page (this is created in memory "RAM" it stored in a hashmap)
		UserDetails adminDeneme = User.builder().username("admin").password("admin").roles("adminDeneme").build();
		
		UserDetails userDeneme = User.builder().username("user").password("user").roles("userDeneme").build();
		
		return new InMemoryUserDetailsManager(adminDeneme,userDeneme);
	}
	
	
	
	
	
	/*
	 * rather than configure this is new solution
	 */
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        
		return  (web) -> 
			web.ignoring().requestMatchers("/users/login");
		
    }
	
	
	
	
	
	
	//this is deprecated ()
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return NoOpPasswordEncoder.getInstance();
		
	}
	
	
	
	/*  probably newer solution for configuration 
	 * 
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception{
		
		
		
		
		httpSec.csrf(csrf -> csrf.disable())
					//hela vela velvela  (that means login endpoint has auth for any users )
				.authorizeHttpRequests(auth -> auth.requestMatchers("/users/login").permitAll().anyRequest().authenticated())
				//there is no session management so user sessions is not managed
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						);
		
		
		
		httpSec.addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class);
		
		httpSec.httpBasic(Customizer.withDefaults());
		
		
		
		
		return httpSec.build();
		
		
		
	}
	
	//semiallahulimenhamide denilen anlar
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	
	
	
}
