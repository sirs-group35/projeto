package sirs.group35.ala.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sirs.group35.ala.service.UserService;
import sirs.group35.ala.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto accountDto) {

        // Print saving to console
        System.out.println("Saving user: " + accountDto.getFirstName() + " " + accountDto.getLastName() + " " + accountDto.getEmail() + " " + accountDto.getPassword());

        userService.saveClient(accountDto);
        return "redirect:/loginUser?registrationSuccess";
    }
}
