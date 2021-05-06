package com.kiran.movieapp.retrofit_services.network_utils;

public interface IDataSourceCallback<T> {
    default void  onDataFound(T data){}
    default void  onDataFound(T data, int responseCode){}
    default void onDataNotFound(){}
    void onError(String error);
}
