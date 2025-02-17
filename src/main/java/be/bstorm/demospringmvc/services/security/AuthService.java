package be.bstorm.demospringmvc.services.security;

import be.bstorm.demospringmvc.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    void register(User user);
}
