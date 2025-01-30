package org.taskntech.tech_flow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.taskntech.tech_flow.data.UserRepository;
import org.taskntech.tech_flow.models.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/manage-account")
    public String showManageAccountPage() {
        // Changed attributes for "name" and "profilePicturePath" to be handled globally - See GlobalControllerAccountSettings
        return "manage-account";
    }

    @PostMapping("/manage-account/update")
    public String updateDisplayName(@RequestParam("displayName") String displayName,
                                    @AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        String accountName = principal.getAttribute("name");
        User user = userRepository.findByEmail(email);

        if (user != null) {
            user.setDisplayName(displayName);
            userRepository.save(user);
        }else{//if user doesn't exist, really should never occur

            User newUser = userRepository.save(new User( email, accountName));
            newUser.setDisplayName(displayName);
            userRepository.save(newUser);

        }
        return "redirect:/manage-account?success=true";
    }

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/";

    @PostMapping("/manage-account/upload-profile-picture")
    public String uploadProfilePicture(@RequestParam("profilePicture") MultipartFile file,
                                       @AuthenticationPrincipal OAuth2User principal) {
        if (file.isEmpty()) {
            return "redirect:/manage-account?error=NoFileUploaded";
        }

        try {
            String email = principal.getAttribute("email");
            String accountName = principal.getAttribute("name");
            User user = userRepository.findByEmail(email);

            if (user != null) {
                String fileName = "profile-" + user.getUserId() + ".png";
                Path uploadPath = Paths.get(UPLOAD_DIRECTORY, fileName);

                Files.createDirectories(uploadPath.getParent());
                Files.write(uploadPath, file.getBytes());
                user.setProfilePicturePath("/uploads/" + fileName);
                userRepository.save(user);

            }else{ //if user doesn't exist, really should never occur
                User newUser = userRepository.save(new User( email, accountName));

                String fileName = "profile-" + newUser.getUserId() + ".png";
                Path uploadPath = Paths.get(UPLOAD_DIRECTORY, fileName);

                Files.createDirectories(uploadPath.getParent());
                Files.write(uploadPath, file.getBytes());
                newUser.setProfilePicturePath("/uploads/" + fileName);
                userRepository.save(newUser);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/manage-account?error=FileUploadFailed";
        }
        return "redirect:/manage-account?profilePictureUploaded=true";
    }

}