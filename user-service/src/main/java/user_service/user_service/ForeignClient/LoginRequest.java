package user_service.user_service.ForeignClient;

import lombok.Data;

@Data
public class LoginRequest {

    
    private String email;
    private String password;
}
