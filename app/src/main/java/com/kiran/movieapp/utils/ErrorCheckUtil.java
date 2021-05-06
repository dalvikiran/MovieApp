package com.kiran.movieapp.utils;

import android.util.Patterns;

public class ErrorCheckUtil {

    public static boolean isEmailValid(String email){
        if (email != null && !email.isEmpty() )
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();

        return false;
    }

    public static boolean isPasswordValid(String password){
        if (password != null && !password.isEmpty() && password.length() >= 6) {
            return true;
        }
        return false;
    }

    public static String checkValidEmail(String email) {
        if (email == null) {
            return "Please enter valid email address";
        } else if (email.trim().isEmpty()) {
            return "Please enter valid email address";
        } else if (email.trim().length() < 6) {
            return "Email id is too short";
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Enter valid email id";
        } else {
            return "";
        }
    }

    public static String checkValidPassword(String password) {
        if (password == null) {
            return "Please enter Password";
        } else if (password.isEmpty()) {
            return "Please enter Password";
        } else if (password.length() < 6) {
            return "Password must have 6 characters";
        } else {
            return "";
        }

    }
}
