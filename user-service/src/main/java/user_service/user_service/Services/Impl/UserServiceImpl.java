package user_service.user_service.Services.Impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import user_service.user_service.Entity.Users;
import user_service.user_service.Exceptions.BadRequest;
import user_service.user_service.ForeignClient.LoginClient;
import user_service.user_service.ForeignClient.LoginRequest; // add this
import user_service.user_service.Records.UserRequest;
import user_service.user_service.Records.UserResponse;
import user_service.user_service.Repository.UserRepository;
import user_service.user_service.Services.UserServices;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final LoginClient loginClient;

	@Override
	@Transactional
	public void CreateUser(UserRequest dto) {
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
		System.out.println(" request email " + dto.info().email());
		loginClient.saveCredentials(request);

	}

	@Override
	public List<UserResponse> getUserById(Long Id) {

		Users user = userRepository.findByUserId(Id)
				.orElseThrow(() -> new BadRequest("Given user Id does not exist: " + Id));

		UserResponse response = new UserResponse(user.getUserId(), user.getUserEmail(), user.getUserFirstName(),
				user.getUserLastName(), user.getUserPhoneNumber(), user.getUserAddress());

		return List.of(response);

	}

	@Override
	public List<UserResponse> getallusers() {
		List<Users> usersList = userRepository.findAll();
		return usersList.stream().map(user -> new UserResponse(user.getUserId(), user.getUserEmail(),
				user.getUserFirstName(), user.getUserLastName(), user.getUserPhoneNumber(), user.getUserAddress()))
				.toList();
	}

}
