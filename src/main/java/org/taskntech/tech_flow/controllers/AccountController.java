package org.taskntech.tech_flow.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Controller
public class AccountController {

    @GetMapping("/manage-account")
    public String showManageAccountPage(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");
            String username = principal.getAttribute("login");

            if (email == null) email = "Set to private";
            if (username == null) username = email;

            Map<String, Object> allAttributes = principal.getAttributes();
            model.addAttribute("name", name);
            model.addAttribute("email", email);
            model.addAttribute("username", username);
            model.addAttribute("allAttributes", allAttributes);
        }
        return "manage-account";
    }

    @PostMapping("/manage-account/update")
    public String updateDisplayName(@RequestParam("displayName") String displayName,
                                    @AuthenticationPrincipal OAuth2User principal) {
        // Save the updated display name to MySQL for use on account management page.
        System.out.println("Updated display name for user, " + principal.getAttribute("email") + ", to " + displayName + ".");

        return "redirect:/manage-account?success=true";
    }

    private static final String UPLOAD_DIRECTORY = "uploads/";

    @PostMapping("/manage-account/upload-profile-picture")
    public String uploadProfilePicture(@RequestParam("profilePicture") MultipartFile file,
                                       @AuthenticationPrincipal OAuth2User principal) {
        if (file.isEmpty()) {
            return "redirect:/manage-account?error=NoFileUploaded";
        }

        try {
            String username = principal.getAttribute("login");
            if (username == null || username.isEmpty()) {
                username = principal.getAttribute("email");
            }

            String fileName = username + ".profile-image";
            Path uploadPath = Paths.get(UPLOAD_DIRECTORY, fileName);
            Files.createDirectories(uploadPath.getParent());
            Files.write(uploadPath, file.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/manage-account?error=FileUploadFailed";
        }
        return "redirect:/manage-account?profilePictureUploaded=true";
    }

}