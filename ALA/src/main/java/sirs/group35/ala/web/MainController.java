package sirs.group35.ala.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/Firm_Organization")
    public String organization() {
        return "organization";
    }

    @GetMapping("/Firm_Contacts")
    public String contacts() {
        return "contacts";
    }

    @GetMapping("/Areas_Of_Activity")
    public String areasOfActivity() {
        return "areas";
    }

    @GetMapping("/loginUser")
    public String login() {
        return "loginUser";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
