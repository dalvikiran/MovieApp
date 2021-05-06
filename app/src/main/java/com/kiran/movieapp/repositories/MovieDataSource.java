package com.kiran.movieapp.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.kiran.movieapp.models.Movie;
import com.kiran.movieapp.retrofit_services.network_utils.IDataSourceCallback;

import java.util.ArrayList;

public abstract class MovieDataSource {

    public void getPopularMovies(@NonNull int pageNo, @NonNull String language, @NonNull IDataSourceCallback<ArrayList<Movie>> callback){}


}
