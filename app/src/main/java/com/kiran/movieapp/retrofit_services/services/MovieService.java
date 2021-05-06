package com.kiran.movieapp.retrofit_services.services;

import com.kiran.movieapp.retrofit_services.network_utils.NetworkURLs;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MovieService {

    @POST(NetworkURLs.GET_POPULAR_MOVIES)
    Call<ResponseBody> getPopularMovies(@Query("api_key") String api_key, @Query("language") String language, @Query("page") int pageNo);

}
