package com.example.cardiacrecorder;

public class Users {

    private String Email, Username, Password, Height, Weight, Gender;

    public Users(String email, String username, String password, String height, String weight, String gender) {
        Email = email;
        Username = username;
        Password = password;
        Height = height;
        Weight = weight;
        Gender = gender;
    }

    public Users() {
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
