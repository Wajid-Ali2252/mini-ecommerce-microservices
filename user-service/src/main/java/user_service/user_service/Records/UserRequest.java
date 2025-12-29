package user_service.user_service.Records;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
		@NotNull(message = "User info is required")
	    @Valid
		UserInfo info,
		@NotBlank(message = "Password cannot be blank")
	    @Size(min = 8, message = "Password must be at least 8 characters")
		String Password) {

}
