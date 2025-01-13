//Most original controller code imported from Doug's OAuth feature branch to run his portion of the app. Added code for display name to update in header across the app.
package org.taskntech.tech_flow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.taskntech.tech_flow.data.UserRepository;
import org.taskntech.tech_flow.models.User;

import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index(Model model, @AuthenticationPrincipal OAuth2User principal){

        if (principal != null) {
            String name = principal.getAttribute("name");
            String username = principal.getAttribute("login");
            String email = principal.getAttribute("email");
            User user = userRepository.findByEmail(email);

            //google doesn't have login/username
            if(username == null) {
                username = email;
            }

            if (user == null) {
                user = new User(email, principal.getAttribute("name"));
                userRepository.save(user);
            }

            Map<String,Object> all = principal.getAttributes();
            model.addAttribute("authenticated", true);
            model.addAttribute("name", user.getDisplayName());
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            model.addAttribute("all", all);
        } else {
            model.addAttribute("authenticated", false);
        }

        return "index";
    }
}