package user_service.user_service.ServiceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import user_service.user_service.Entity.Users;
import user_service.user_service.Exceptions.BadRequest;
import user_service.user_service.ForeignClient.LoginClient;
import user_service.user_service.ForeignClient.LoginRequest;  // add this
import user_service.user_service.Records.UserRequest;
import user_service.user_service.Repository.UserRepository;
import user_service.user_service.Services.UserServices;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {
	
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final LoginClient loginClient;
	
	
	
	@Override
	public void CreateUser(UserRequest dto)
	{
		userRepository.findByUserEmail(dto.info().email()).ifPresent(user -> {
		    throw new BadRequest("Given Email already exists: " + dto.info().email());
		});
		String password = passwordEncoder.encode(dto.Password());
		Users users = new Users();
		users.setPassword(password);
		users.setUserFirstName(dto.info().firstName());
		users.setUserLastName(dto.info().lastName());
		users.setUserEmail(dto.info().email());
		users.setUserPhoneNumber(dto.info().phoneNo());
		users.setUserAddress(dto.info().address());
		userRepository.save(users);
		
	    LoginRequest request = new LoginRequest();
	    request.setEmail(dto.info().email());
	    request.setPassword(password);
	    System.out.println(" request email "+dto.info().email());
	    loginClient.saveCredentials(request);
		
	}

}
