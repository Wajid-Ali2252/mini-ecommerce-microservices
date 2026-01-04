package login_service.login_service;

public record AuthRecord(String accessToken,
    String tokenType,
    long expiresInMints,
    String refreshToken)
{	
}
