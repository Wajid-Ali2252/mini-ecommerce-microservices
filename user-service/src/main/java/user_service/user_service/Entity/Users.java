package user_service.user_service.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	private String userFirstName;
	
	private String userLastName;
	
	private String userEmail;
	
	private String userPhoneNumber;
	
	private String userAddress;
	
	private String Password;
	

}
