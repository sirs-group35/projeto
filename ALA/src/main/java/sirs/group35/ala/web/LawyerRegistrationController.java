package sirs.group35.ala.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sirs.group35.ala.service.UserService;
import sirs.group35.ala.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/manager/registration-lawyer")
public class LawyerRegistrationController {

    private final UserService userService;

    public LawyerRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration-lawyer";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto accountDto) {

        // Print saving to console
        System.out.println("Saving Lawyer: " + accountDto.getFirstName() + " " + accountDto.getLastName() + " " + accountDto.getEmail() + " " + accountDto.getPassword());

        userService.saveLawyer(accountDto);
        return "redirect:/manager/registration-lawyer?success";
    }
}
