package com.example.hackschealthcare;

public class Profile {
    String uniqueId;
    int profileImage;
    String nameAge;

    // Assuming there's a constructor that initializes these fields

    // Getters
    public String getUniqueId() {
        return uniqueId;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public String getNameAge() {
        return nameAge;
    }

    // Setters
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public void setNameAge(String nameAge) {
        this.nameAge = nameAge;
    }
}
