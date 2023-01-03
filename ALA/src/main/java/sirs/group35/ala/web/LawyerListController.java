package sirs.group35.ala.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sirs.group35.ala.repository.LawyerRepository;

@Controller
@RequestMapping("/manager/list-lawyers")
public class LawyerListController {

    @Autowired
    private LawyerRepository lawyerRepository;

    @GetMapping
    public ModelAndView getAllLawyers() {
        ModelAndView mav = new ModelAndView("list-lawyers");
        mav.addObject("lawyers", lawyerRepository.findAll());
        return mav;
    }
}