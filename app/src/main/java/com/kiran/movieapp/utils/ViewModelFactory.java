package com.kiran.movieapp.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kiran.movieapp.view_models.LoginViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final Application application;
//    private static LifecycleOwner owner;
//    private static View view;

//    public static ViewModelFactory getInstance(Activity activity, LifecycleOwner owner, View viewObj) {
    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
//                    INSTANCE = new ViewModelFactory(activity, owner, viewObj);
                    INSTANCE = new ViewModelFactory(application);
                }
            }

        }
//        INSTANCE.owner = owner;
//        INSTANCE.view = viewObj;
        return INSTANCE;
    }

//    public ViewModelFactory(Activity activity, LifecycleOwner owner, View view) {
    public ViewModelFactory(Application application) {
        this.application = application;
//        this.owner = owner;
//        this.view = view;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //noinspection unchecked
            return (T) LoginViewModel.getInstance(application);
        }

       /* if (modelClass.isAssignableFrom(VerifyMobileNoViewModel.class)) {
            //noinspection unchecked
            return (T) new VerifyMobileNoViewModel(mApplication, owner, view, ApplicationRepository.getInstance(mApplication, true));
        }*/

        /*if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //noinspection unchecked
            return (T) new LoginViewModel(mApplication, owner, view, UserRepository.getInstance(mApplication, true));
        }

        if (modelClass.isAssignableFrom(OtpVerificationViewModel.class)) {
            //noinspection unchecked
            return (T) new OtpVerificationViewModel(mApplication, owner, view, UserRepository.getInstance(mApplication, true));

        }

        if (modelClass.isAssignableFrom(SetPasswordViewModel.class)) {
            //noinspection unchecked
            return (T) new SetPasswordViewModel(mApplication, owner, view, UserRepository.getInstance(mApplication, true));

        }
        if (modelClass.isAssignableFrom(ForgotPasswordViewModel.class)) {
            //noinspection unchecked
            return (T) new ForgotPasswordViewModel(mApplication, owner, view, UserRepository.getInstance(mApplication, true));

        }

        if (modelClass.isAssignableFrom(ChangePasswordViewModel.class)) {
            //noinspection unchecked
            return (T) new ChangePasswordViewModel(mApplication, owner, view, UserRepository.getInstance(mApplication, true));

        }*/

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
