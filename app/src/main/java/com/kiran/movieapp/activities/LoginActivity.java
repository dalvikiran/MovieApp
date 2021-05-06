package com.kiran.movieapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.kiran.movieapp.R;
import com.kiran.movieapp.databinding.ActivityLoginBinding;
import com.kiran.movieapp.models.User;
import com.kiran.movieapp.utils.ViewModelFactory;
import com.kiran.movieapp.view_models.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        ViewModelFactory viewModelFactory = new ViewModelFactory(getApplication());
        loginViewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel.class);

        activityLoginBinding.setLoginViewModel(loginViewModel);
        activityLoginBinding.setLifecycleOwner(this);

        loginViewModel.email.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                loginViewModel.validateData();
            }
        });

        loginViewModel.password.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                loginViewModel.validateData();
            }
        });



    }
}