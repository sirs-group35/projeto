package sirs.group35.ala.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sirs.group35.ala.service.UserService;

@Controller
public class LawyerDeleteController {
    private final UserService userService;

    public LawyerDeleteController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/manager/delete-lawyer")
    public String deleteLawyer(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/manager/list-lawyers?deleteSuccess";
    }
}