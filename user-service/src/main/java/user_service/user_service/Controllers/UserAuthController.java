package user_service.user_service.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import user_service.user_service.ApiResponse.ApiResponse;
import user_service.user_service.Exceptions.BadRequest;
import user_service.user_service.Records.UserRequest;
import user_service.user_service.Services.UserServices;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserAuthController {
 
	private final UserServices userServices;
	
	@GetMapping("/health")
	public String healthcheck()
	{
		return "health OK";
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<?>>  createUser(@Valid @RequestBody UserRequest dto)
	{
		
		
		try {
			userServices.CreateUser(dto);
			return ResponseEntity.status(HttpStatus.CREATED)
		            .body(ApiResponse.success(
		                    "User registered successfully",null
		            ));
		}catch (BadRequest ex) {
	        
	        throw ex; 
	    } catch (Exception ex) {
	        throw new RuntimeException("System error: " + ex.getMessage());
	    }
		
	
	}
}
