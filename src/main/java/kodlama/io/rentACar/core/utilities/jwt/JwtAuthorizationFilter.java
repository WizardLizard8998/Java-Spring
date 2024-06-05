package kodlama.io.rentACar.core.utilities.jwt;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
	
	
	private final JwtUtil jwtUtil;
	
	
	public JwtAuthorizationFilter(JwtUtil jwtUtil) {
		this.jwtUtil=jwtUtil;
	}
	
	
	
	// çince daha anlaşılır 

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 String accessToken = jwtUtil.resolveToken(request);
         if (accessToken == null ) {
             filterChain.doFilter(request, response);
             return;
         }
		
         System.out.println("token : "+accessToken);
         Claims claims = jwtUtil.resolveClaims(request);
         
         if(claims != null & jwtUtil.validateClaims(claims)){
             String username = claims.getSubject();
             System.out.println("username : "+username);
             Authentication authentication =
                     new UsernamePasswordAuthenticationToken(username,"",new ArrayList<>());
             SecurityContextHolder.getContext().setAuthentication(authentication);
         }
		
		
         filterChain.doFilter(request, response);
         
	}

}
