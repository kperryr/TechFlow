package org.taskntech.tech_flow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.taskntech.tech_flow.data.UserRepository;


@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            model.addAttribute("authenticated", true);
        } else {
            model.addAttribute("authenticated", false);
        }
        return "index";
    }
}