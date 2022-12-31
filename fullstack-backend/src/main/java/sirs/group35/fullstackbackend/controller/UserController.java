package sirs.group35.fullstackbackend.controller;

import sirs.group35.fullstackbackend.dto.LegalCaseDTO;
import sirs.group35.fullstackbackend.dto.UserDTO;
import sirs.group35.fullstackbackend.exception.UserNotFoundException;
import sirs.group35.fullstackbackend.services.CaseService;
import sirs.group35.fullstackbackend.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService = new UserService();

    @PostMapping("/user")
    UserDTO newUser(@RequestBody UserDTO newUserDTO) {
        return userService.registerUser(newUserDTO);
    }

    @GetMapping("/lawyers")
    List<UserDTO> getAllLawyers() {
        return userService.listAllLayers();
    }

    @GetMapping("/clients")
    List<UserDTO> getAllClients() {
        return userService.listAllClients();
    }

    @GetMapping("/users")
    List<UserDTO> getAllUsers() {
        return userService.listAllUsers();
    }
}
