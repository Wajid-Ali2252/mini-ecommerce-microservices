package api_gateway.api_gateway;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class Jwtutils {

	
	private String secret = "asd9305#4@@%648327#4%$%638292@asbs";
	
	public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
           return false;
        }
    }
}
