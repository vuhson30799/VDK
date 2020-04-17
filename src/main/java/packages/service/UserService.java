package packages.service;

import org.springframework.stereotype.Service;
import packages.model.User;

@Service
public interface UserService {
    User findByEmail(String email);
    User findByUserName(String userName);
    User save(User user);
}
