package com.kiran.movieapp.view_models;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kiran.movieapp.activities.MainActivity;
import com.kiran.movieapp.models.User;
import com.kiran.movieapp.utils.ErrorCheckUtil;

public class LoginViewModel extends AndroidViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>("");
    public MutableLiveData<Boolean> busy = new MutableLiveData<>(Boolean.FALSE);
    public MutableLiveData<Boolean> loginButtonEnabled = new MutableLiveData<>(Boolean.FALSE);

    public final MutableLiveData<String> errorEmail = new MutableLiveData<>();
    public final MutableLiveData<String> errorPassword = new MutableLiveData<>();

//    private MutableLiveData<User> userMutableLiveData;

    private static LoginViewModel loginViewModel;
    private Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }


    public static synchronized LoginViewModel getInstance(Application application) {
        if (loginViewModel == null) {
            loginViewModel = new LoginViewModel(application);
            return loginViewModel;
        }
        return loginViewModel;
    }



    public void validateData(){
        String email = this.email.getValue();
        String password = this.password.getValue();
        if (ErrorCheckUtil.isEmailValid(email) && ErrorCheckUtil.isPasswordValid(password)){
            loginButtonEnabled.setValue(true);
        }else{
            loginButtonEnabled.setValue(false);
        }
    }

    public void onLoginClicked() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
