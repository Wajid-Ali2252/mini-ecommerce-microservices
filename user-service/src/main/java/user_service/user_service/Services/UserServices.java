package user_service.user_service.Services;

import java.util.List;

import user_service.user_service.Records.UserRequest;
import user_service.user_service.Records.UserResponse;

public interface UserServices {
    void CreateUser(UserRequest dto);
    List<UserResponse> getUserById(Long Id);
    List<UserResponse> getallusers();
}
