package login_service.login_service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="credentionals")
@Data
public class LoginRequest {
	
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long Id;
   private String email;
   private String password;
}
