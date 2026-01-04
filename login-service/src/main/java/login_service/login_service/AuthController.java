package login_service.login_service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	
	    private final LoginRepository loginRepository;
	    private final AuthenticationManager authenticationManager;
	    private final Jwtutils jwtutils;
	
	    @PostMapping("/credentials")
	    public void saveCredentials(@RequestBody LoginRequest request) {
	    	System.out.println("Login Service Received Email: " + request.getEmail());
	        loginRepository.save(request);
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
	    	
	     try {
	    	 
	     
	        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
	        );
	        String jwt = jwtutils.generateToken(authentication);
	        Map<String, Object> data = new HashMap<>();
	        data.put("accessToken", jwt);
	        data.put("tokenType", "Bearer");
	        data.put("expiresInMints", 1440);
	        data.put("refreshToken", UUID.randomUUID().toString());

	       
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", true);
	        response.put("status", 200);
	        response.put("message", "Login successful.");
	        response.put("data", data);

	        return ResponseEntity.ok(response);
	     } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
	        
	         return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                 .body(new ApiResponse<>(false, 401, "Invalid Username or Password!", null));
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                 .body(new ApiResponse<>(false, 500, "Something went wrong: " + e.getMessage(), null));
	     }
	       
	    }

}
