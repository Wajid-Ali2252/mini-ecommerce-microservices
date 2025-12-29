package user_service.user_service.Records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserInfo(
		@NotBlank(message = "Name is required")
		String firstName,
		@NotBlank(message = "Name is required")
		String lastName,
		
		@Email(message = "Invalid email format")
		@NotBlank(message = "Email is required")
		String email,
		@NotBlank(message = "PhoneNo is required")
		String phoneNo,
		@NotBlank(message = "Address is required")
		String address) {
	
}

