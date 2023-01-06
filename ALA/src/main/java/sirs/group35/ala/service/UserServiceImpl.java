package sirs.group35.ala.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sirs.group35.ala.model.Client;
import sirs.group35.ala.model.Lawyer;
import sirs.group35.ala.model.Role;
import sirs.group35.ala.model.User;
import sirs.group35.ala.repository.RoleRepository;
import sirs.group35.ala.repository.UserRepository;
import sirs.group35.ala.web.dto.UserRegistrationDto;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        super();
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    // Map roles to authorities function
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public Client saveClient(UserRegistrationDto registrationDto) {
        Role role = roleRepository.findByName("ROLE_CLIENT");
        if (role == null) {
            role = new Role("ROLE_CLIENT");
            roleRepository.save(role);
        }
        Client user = new Client(registrationDto.getFirstName(), registrationDto.getLastName(),
                registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()),
                List.of(role));
        return userRepository.save(user);
    }

    @Override
    public Lawyer saveLawyer(UserRegistrationDto registrationDto) {
        Role role = roleRepository.findByName("ROLE_LAWYER");
        if (role == null) {
            role = new Role("ROLE_LAWYER");
            roleRepository.save(role);
        }
        Lawyer user = new Lawyer(registrationDto.getFirstName(), registrationDto.getLastName(),
                registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()),
                List.of(role));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}

