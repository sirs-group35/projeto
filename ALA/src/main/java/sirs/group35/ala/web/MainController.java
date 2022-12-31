package sirs.group35.ala.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value="/loginUser", method= RequestMethod.GET)
    public String login() {
        return "loginUser";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
