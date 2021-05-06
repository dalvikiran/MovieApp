package com.kiran.movieapp.models;


import android.util.Patterns;

public class User {
    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }


    public String getEmail() {
        if (email == null) {
            return "";
        }
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPassword() {
        if (password == null) {
            return "";
        }
        return password;
    }

}