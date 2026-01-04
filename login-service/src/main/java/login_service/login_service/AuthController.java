package login_service.login_service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
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
	private final PasswordEncoder passwordEncoder;
	private final Jwtutils jwtutils;

	@PostMapping("/credentials")
	public void saveCredentials(@RequestBody LoginRequest request) {
		System.out.println("Login Service Received Email: " + request.getEmail());
		loginRepository.save(request);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

		

			LoginRequest user = loginRepository.findByEmail(loginRequest.getEmail())
					.orElseThrow(() -> new BadRequestException("User not found"));

			if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
				throw new BadRequestException("Invalid credentials");
			}

			String jwt = jwtutils.generateToken(loginRequest.getEmail());
			Map<String, Object> data = new HashMap<>();
			data.put("accessToken", jwt);
			data.put("tokenType", "Bearer");
			data.put("expiresInMints", 1440);

			Map<String, Object> response = new HashMap<>();
			response.put("success", true);
			response.put("status", 200);
			response.put("message", "Login successful.");
			response.put("data", data);

			return ResponseEntity.ok(response);
		

	}

}
