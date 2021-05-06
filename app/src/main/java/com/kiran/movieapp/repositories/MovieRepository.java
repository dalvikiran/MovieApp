package com.kiran.movieapp.repositories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.kiran.movieapp.models.Movie;
import com.kiran.movieapp.retrofit_services.network_utils.IDataSourceCallback;

import java.util.ArrayList;

public class MovieRepository extends MovieDataSource {

    private volatile static MovieRepository INSTANCE = null;
    private final MovieDataSource movieRemoteDataSource;

    public MovieRepository(MovieDataSource movieRemoteDataSource) {
        this.movieRemoteDataSource = movieRemoteDataSource;
    }

    public static MovieRepository getInstance(@NonNull Application mApplication, boolean initRemoteRepository) {
        if (INSTANCE == null) {
            synchronized (MovieRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieRepository(initRemoteRepository ? MovieRemoteDataSource.getInstance(mApplication) : null);

                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getPopularMovies(@NonNull int pageNo, @NonNull String language, @NonNull IDataSourceCallback<ArrayList<Movie>> callback) {
        movieRemoteDataSource.getPopularMovies(pageNo, language, callback);
    }
}
