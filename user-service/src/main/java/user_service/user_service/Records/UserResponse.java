package user_service.user_service.Records;

public record UserResponse(Long Id, UserInfo info) {


    public UserResponse(Long userId, String userEmail, String userFirstName, String userLastName,
                        String userPhoneNumber, String userAddress) {
       
        this(userId, new UserInfo(userEmail, userFirstName, userLastName, userPhoneNumber, userAddress));
    }
}