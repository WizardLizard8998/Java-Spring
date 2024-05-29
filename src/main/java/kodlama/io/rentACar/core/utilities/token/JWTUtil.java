package kodlama.io.rentACar.core.utilities.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtil {
	
	// hash key 
    private String SECRET_KEY = "damnson";

    // returns tokens username 
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // expire date
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // return claims 
    /*
     * claim is one key value pair in token 
     *    
     * ex: "typ":"JWT"  -> a key defines token type 
     * 
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // does token expired 
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // generate token
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject) // our user
                .setIssuedAt(new Date(System.currentTimeMillis())) // token born date
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000)) // expire date
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // algorithm for sign and our secret key "damnson"
                .compact();
    }

    // does token ok ? is it still usable check it 
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
	
}
