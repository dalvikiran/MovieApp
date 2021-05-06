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

       /* SingletonNameViewModelFactory singletonNameViewModelFactory = new SingletonNameViewModelFactory(MyViewModel.getInstance());
        MyViewModel viewModel = ViewModelProviders.of(this,singletonNameViewModelFactory).get(MyViewModel.class);
        viewModel.getData();
*/
        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        ViewModelFactory viewModelFactory = new ViewModelFactory(getApplication());
        loginViewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel.class);

//         loginViewModel = new LoginViewModel();
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

        /*loginViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@NonNull User user) {
                if (user.getEmail().length() > 0 || user.getPassword().length() > 0)
                    Toast.makeText(getApplicationContext(),
                            "email : " + user.getEmail() + " password " + user.getPassword(),
                            Toast.LENGTH_SHORT).show();

            }
        });*/

    }
}