package sirs.group35.ala.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import sirs.group35.ala.model.Lawyer;
import sirs.group35.ala.model.User;
import sirs.group35.ala.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
    User saveClient(UserRegistrationDto registrationDto);
    Lawyer saveLawyer(UserRegistrationDto registrationDto);
    void deleteUser(Long id);
}
