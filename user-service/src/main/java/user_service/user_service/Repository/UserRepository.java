package user_service.user_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import user_service.user_service.Entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

	
}
