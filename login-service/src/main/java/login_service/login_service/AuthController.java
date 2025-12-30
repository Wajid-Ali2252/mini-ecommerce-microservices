package login_service.login_service;

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
	
	    @PostMapping("/credentials")
	    public void saveCredentials(@RequestBody LoginRequest request) {
	    	System.out.println("Login Service Received Email: " + request.getEmail());
	        loginRepository.save(request);
	    }

}
