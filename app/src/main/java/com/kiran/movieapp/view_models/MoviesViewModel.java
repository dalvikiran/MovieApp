package com.kiran.movieapp.view_models;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.kiran.movieapp.models.Movie;
import com.kiran.movieapp.repositories.MovieRepository;
import com.kiran.movieapp.retrofit_services.network_utils.IDataSourceCallback;
import com.kiran.movieapp.utils.network.InternetConnectionUtil;

import java.util.ArrayList;

public class MoviesViewModel extends AndroidViewModel {

    private static MoviesViewModel moviesViewModel;
    private Context context;
    private MovieRepository movieRepository;
    public MutableLiveData<ArrayList<Movie>> moviesListMutableLiveData = new MutableLiveData<>();
    private String movieLanguage = "en-US";

    public MutableLiveData<Boolean> loading = new MutableLiveData<>(Boolean.FALSE);
    public int pageNo = 0;


    public MoviesViewModel(@NonNull Application application) {
        super(application);
        context = application;

        movieRepository = MovieRepository.getInstance(application, true);
    }


    public static synchronized MoviesViewModel getInstance(Application application) {
        if (moviesViewModel == null) {
            moviesViewModel = new MoviesViewModel(application);
            return moviesViewModel;
        }
        return moviesViewModel;
    }

    public void getPopularMovies(){

        if (InternetConnectionUtil.getConnectivityStatus(context) > 0){
            pageNo++;
            loading.setValue(true);
            movieRepository.getPopularMovies(pageNo, movieLanguage,
                    new IDataSourceCallback<ArrayList<Movie>>() {
                        @Override
                        public void onDataFound(ArrayList<Movie> data) {
                            moviesListMutableLiveData.setValue(data);
                        }

                        @Override
                        public void onDataNotFound() {
                            Toast.makeText(context, "No movies found", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(String error) {
                            Toast.makeText(context, "Error : " + error, Toast.LENGTH_LONG).show();
                        }
                    });
        }else{
            Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_LONG).show();

        }

    }

}
