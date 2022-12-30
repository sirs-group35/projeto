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

    @Autowired
    private CaseService caseService = new CaseService();

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

    @GetMapping("/legalCases")
    List<LegalCaseDTO> getAllLegalCases() {
        return caseService.listAllLegalCases();
    }

    @PostMapping("/legalCase")
    LegalCaseDTO registerCase(@RequestBody LegalCaseDTO newLegalCaseDTO) {
        return caseService.registerLegalCase(newLegalCaseDTO);
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
