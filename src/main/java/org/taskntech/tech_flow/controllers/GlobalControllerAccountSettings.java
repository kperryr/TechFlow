package org.taskntech.tech_flow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.taskntech.tech_flow.data.UserRepository;
import org.taskntech.tech_flow.models.User;
import org.springframework.ui.Model;

@Controller
@RestControllerAdvice
public class GlobalControllerAccountSettings {

    @Autowired
    private UserRepository userRepository;

    private static final String DEFAULT_PROFILE_PICTURE_URL = "https://www.tenforums.com/attachments/user-accounts-family-safety/322690d1615743307-user-account-image-log-user.png";

    @ModelAttribute
    public void addGlobalAttributes(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal != null) {
            String email = principal.getAttribute("email");
            String name = principal.getAttribute("name");
            User user = userRepository.findByEmail(email);

            if (user != null) {
                model.addAttribute("name", user.getDisplayName());
                model.addAttribute("profilePicturePath", user.getProfilePicturePath() != null ? user.getProfilePicturePath() : DEFAULT_PROFILE_PICTURE_URL);
            } else {
                //creates new instance of a user
                User newUser = userRepository.save(new User( email, name));
                model.addAttribute("name", principal.getAttribute("name"));
                model.addAttribute("profilePicturePath", DEFAULT_PROFILE_PICTURE_URL);
            }
        } else {
            model.addAttribute("name", "Not Signed In");
            model.addAttribute("profilePicturePath", DEFAULT_PROFILE_PICTURE_URL);
        }
    }
}