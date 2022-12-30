package sirs.group35.fullstackbackend.controller;

import sirs.group35.fullstackbackend.dto.CaseDTO;
import sirs.group35.fullstackbackend.dto.UserDTO;
import sirs.group35.fullstackbackend.exception.UserNotFoundException;
import sirs.group35.fullstackbackend.model.Lawyer;
import sirs.group35.fullstackbackend.model.User;
import sirs.group35.fullstackbackend.repository.CaseRepository;
import sirs.group35.fullstackbackend.services.CaseService;
import sirs.group35.fullstackbackend.services.UserService;
import sirs.group35.fullstackbackend.model.Case;
import sirs.group35.fullstackbackend.model.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    UserService userService;

    CaseService caseService;

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

    @GetMapping("/cases")
    List<CaseDTO> getAllCases() {
        return caseService.listAllCases();
    }

    @PutMapping("/case")
    CaseDTO registerCase(@RequestBody CaseDTO newCaseDTO) {
        return caseService.registerCase(newCaseDTO);
    }

    // @GetMapping("/user/{id}")
    // User getUserById(@PathVariable Long id) {
    //     return userRepository.findById(id)
    //             .orElseThrow(() -> new UserNotFoundException(id));
    // }

    // @PutMapping("/user/{id}")
    // User updateUser(@RequestBody User newUser, @PathVariable Long id) {
    //     return userRepository.findById(id)
    //             .map(user -> {
    //                 user.setUsername(newUser.getUsername());
    //                 user.setName(newUser.getName());
    //                 user.setEmail(newUser.getEmail());
    //                 return userRepository.save(user);
    //             }).orElseThrow(() -> new UserNotFoundException(id));
    // }

    // @DeleteMapping("/user/{id}")
    // String deleteUser(@PathVariable Long id){
    //     if(!userRepository.existsById(id)){
    //         throw new UserNotFoundException(id);
    //     }
    //     userRepository.deleteById(id);
    //     return  "User with id "+id+" has been deleted success.";
    // }

}
