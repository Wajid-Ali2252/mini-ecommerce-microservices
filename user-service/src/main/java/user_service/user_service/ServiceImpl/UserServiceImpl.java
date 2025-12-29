package user_service.user_service.ServiceImpl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import user_service.user_service.Records.UserRequest;
import user_service.user_service.Repository.UserRepository;
import user_service.user_service.Services.UserServices;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {
	
	
	private final UserRepository userRepository;
	
	
	@Override
	public void CreateUser(UserRequest dto)
	{
		
	}

}
