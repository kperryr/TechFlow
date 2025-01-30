package org.taskntech.tech_flow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    //updated to remove display name and email, parent call has name and email

    private String profilePicturePath;

    public User() {
        super("","");// calls to parent class
    }

    public User(String email, String name){
        super(email,name); //calls to parent class
        this.profilePicturePath = "https://www.tenforums.com/attachments/user-accounts-family-safety/322690d1615743307-user-account-image-log-user.png";
    }

    public int getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return this.name; //get parent class value
    }

    public void setDisplayName(String displayName) {
        this.name = displayName; //set parent class value
    }

    public String getProfilePicturePath() {
        return profilePicturePath != null && !profilePicturePath.isEmpty()
                ? profilePicturePath
                : "https://www.tenforums.com/attachments/user-accounts-family-safety/322690d1615743307-user-account-image-log-user.png";
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }
}