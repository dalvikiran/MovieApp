package com.kiran.movieapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.kiran.movieapp.R;
import com.kiran.movieapp.adapters.MoviesAdapter;
import com.kiran.movieapp.databinding.ActivityMainBinding;
import com.kiran.movieapp.models.Movie;
import com.kiran.movieapp.utils.ViewModelFactory;
import com.kiran.movieapp.view_models.MoviesViewModel;
import com.kiran.movieapp.view_models.MoviesViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MoviesViewModel moviesViewModel;
    private MoviesAdapter moviesAdapter;

    private LinearLayoutManager mLayoutManager;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        RecyclerView recyclerView = activityMainBinding.moviesRecyclerView;

        mLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        ViewModelFactory viewModelFactory = new ViewModelFactory(getApplication());
        moviesViewModel = ViewModelProviders.of(this,viewModelFactory).get(MoviesViewModel.class);

        activityMainBinding.setLifecycleOwner(this);

        moviesAdapter = new MoviesAdapter(this);
        recyclerView.setAdapter(moviesAdapter);

        moviesViewModel.getPopularMovies();



        moviesViewModel.moviesListMutableLiveData.observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                if (moviesViewModel.pageNo == 1)
                    moviesAdapter.setMovieList(movies);
                else
                    moviesAdapter.addNewMovies(movies);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (moviesViewModel.loading.getValue()) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            moviesViewModel.loading.setValue(false);
//                            Log.v("...", "Last Item Wow !");
                            // Do pagination.. i.e. fetch new data

                            moviesViewModel.getPopularMovies();
                            moviesViewModel.loading.setValue(true);

                        }
                    }
                }
            }
        });



    }
}