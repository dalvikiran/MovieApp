package com.kiran.movieapp.retrofit_services.network_utils;

public interface NetworkResponseCallback {

    void onSuccess(String jsonData);

    void onError(int errorCode, String errorData);

}
