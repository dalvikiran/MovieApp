package com.kiran.movieapp.retrofit_services;

import android.content.Context;
import androidx.annotation.NonNull;
import com.kiran.movieapp.R;
import com.kiran.movieapp.retrofit_services.network_utils.NetworkResponseCallback;
import com.kiran.movieapp.retrofit_services.network_utils.NetworkUtils;
import com.kiran.movieapp.retrofit_services.services.MovieService;

import org.json.JSONObject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkController {

    private static NetworkController instance;
    private static Context mContext;
    public static String API_KEY = "aa4fd4e1a877060570c5f5b6e292f1b4";

    public static final String SERVER_ERROR = "Something went wrong on the server";


    public synchronized static NetworkController getInstance(Context context) {
        mContext = context;

        if (instance == null) {
            instance = new NetworkController();
        }
        return instance;
    }


    private class RetrofitServiceTask implements Callback<ResponseBody> {

        NetworkResponseCallback networkResponseCallback;

        public RetrofitServiceTask(NetworkResponseCallback networkResponseCallback) {
            this.networkResponseCallback = networkResponseCallback;
        }

        @Override
        public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
             if (response.code() == NetworkUtils.HTTP_SUCCESS) {
                String responseString = NetworkUtils.getStringResponseFromRaw(response);
//                Log.e("RESPONSE SUCCESS", responseString);
                networkResponseCallback.onSuccess(responseString);

            } else {
                String errorMsg = SERVER_ERROR;
                String jsonError = NetworkUtils.getStringResponseFromRaw(response.errorBody());
//                Log.e("RESPONSE ERROR", jsonError);
                try {
                    JSONObject jsonObject = new JSONObject(jsonError);

                    errorMsg = jsonObject.optString(response.code() + "");
                } catch (Exception e) {
                    errorMsg = SERVER_ERROR;
                }
                if (errorMsg.isEmpty()) {
                    errorMsg = SERVER_ERROR;
                }
                networkResponseCallback.onError(response.code(), errorMsg);

            }
        }

        @Override
        public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
            if (t.getMessage() != null) {
                if (t.getMessage().contains("Failed") || t.getMessage().contains("failed to connect")) {
                    networkResponseCallback.onError(NetworkUtils.HTTP_RETROFIT_FAILURE, mContext.getResources().getString(R.string.network_failure_string));
                }
            }
        }
    }

    public void getPopularMovies(@NonNull int pageNo, @NonNull String language, NetworkResponseCallback callback) {
        Retrofit retrofit = NetworkUtils.buildRetrofit(true, mContext);
        MovieService service = retrofit.create(MovieService.class);
        Call<ResponseBody> moviesResponseCall = service.getPopularMovies(API_KEY, language,pageNo);
        moviesResponseCall.enqueue(new RetrofitServiceTask(callback));
    }

}
