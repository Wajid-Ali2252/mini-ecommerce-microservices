package user_service.user_service.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import user_service.user_service.Records.UserRequest;
import user_service.user_service.Services.UserServices;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserAuthController {
 
	private final UserServices userServices;
	
	
	@PostMapping("/register")
	public ResponseEntity<?>  createUser(@Valid @RequestBody UserRequest dto)
	{
		userServices.CreateUser(dto);
	 return ResponseEntity.ok("user Created Successfully");
	}
}
