package sirs.group35.ala.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import sirs.group35.ala.model.User;
import sirs.group35.ala.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
