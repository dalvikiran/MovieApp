package com.kiran.movieapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kiran.movieapp.R;
import com.kiran.movieapp.databinding.MovieItemLayoutBinding;
import com.kiran.movieapp.models.Movie;
import com.kiran.movieapp.retrofit_services.network_utils.NetworkURLs;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    Context context;
    ArrayList<Movie> movieArrayList = new ArrayList<>();

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    public void setMovieList(ArrayList<Movie> movieArrayList){
        this.movieArrayList = movieArrayList;
        notifyDataSetChanged();
    }

    public void addNewMovies(ArrayList<Movie> movieArrayList){
        this.movieArrayList.addAll(movieArrayList);
        notifyItemRangeInserted(movieArrayList.size(), movieArrayList.size());
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieItemLayoutBinding movieItemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.movie_item_layout,parent,false);
        return new MovieViewHolder(movieItemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie = movieArrayList.get(position);
        if (movie != null){
            holder.movieItemLayoutBinding.setMovie(movie);

           /* if (movie.getTitle().length() > 18){
                holder.movieItemLayoutBinding.movieNameTextView.startAnimation(
                        (Animation) AnimationUtils.loadAnimation(context,R.anim.scroll_animation));
            }*/

        }
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        MovieItemLayoutBinding movieItemLayoutBinding;

        public MovieViewHolder(@NonNull MovieItemLayoutBinding movieItemLayoutBinding) {
            super(movieItemLayoutBinding.getRoot());
            this.movieItemLayoutBinding = movieItemLayoutBinding;
        }
    }



}
