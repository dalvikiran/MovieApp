package com.kiran.movieapp.repositories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kiran.movieapp.models.Movie;
import com.kiran.movieapp.retrofit_services.NetworkController;
import com.kiran.movieapp.retrofit_services.network_utils.IDataSourceCallback;
import com.kiran.movieapp.retrofit_services.network_utils.NetworkResponseCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieRemoteDataSource extends MovieDataSource {

    private static MovieRemoteDataSource instance;
    private static NetworkController networkController;

    private static Context mContext;

    public static MovieRemoteDataSource getInstance(Context context) {
        mContext = context;
        networkController = NetworkController.getInstance(context);
        if (instance == null) {
            instance = new MovieRemoteDataSource();
        }
        return instance;
    }

    public MovieRemoteDataSource() {
    }

    @Override
    public void getPopularMovies(@NonNull int pageNo, @NonNull String language,
                                 @NonNull IDataSourceCallback<ArrayList<Movie>> callback) {
        networkController.getPopularMovies(pageNo, language, new NetworkResponseCallback() {
            @Override
            public void onSuccess(String jsonData) {
                if (jsonData != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(jsonData);
                        if(jsonObject.has("results")){
                            JSONArray movieListJsonArray = jsonObject.getJSONArray("results");
                            if (movieListJsonArray.length() > 0) {
                                Gson gson = new Gson();
                                ArrayList<Movie> movieArrayList =
                                        gson.fromJson(movieListJsonArray.toString(),
                                                new TypeToken<ArrayList<Movie>>() {
                                                }.getType());

                                callback.onDataFound(movieArrayList);
                            } else {
                                callback.onDataNotFound();
                            }
                        } else {
                            callback.onDataNotFound();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(int errorCode, String errorData) {
                callback.onError(errorData);
            }
        });
    }

}
