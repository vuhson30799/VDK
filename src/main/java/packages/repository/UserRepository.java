package packages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import packages.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByUsername(String username);
}
