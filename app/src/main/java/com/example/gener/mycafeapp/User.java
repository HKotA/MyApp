package com.example.gener.mycafeapp;

public class User {

    private String Name;
    private String Email;
    private String Password;
    private String Phone;

    public User(){

    }

    public User(String name, String email, String password, String phone) {
        Email = email;
        Password = password;
        Phone = phone;
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
