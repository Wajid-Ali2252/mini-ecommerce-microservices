package user_service.user_service.ForeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "login-service") 
public interface LoginClient {
	 @PostMapping("/api/v1/auth/credentials")
	 void saveCredentials(@RequestBody LoginRequest loginRequest);
}

