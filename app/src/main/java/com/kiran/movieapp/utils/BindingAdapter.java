package com.kiran.movieapp.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kiran.movieapp.R;
import com.kiran.movieapp.retrofit_services.network_utils.NetworkURLs;

public class BindingAdapter {

    @androidx.databinding.BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url){
        if (url != null && !url.isEmpty()){
            Glide.with(view.getContext())
                    .load(NetworkURLs.IMAGE_BASE_URL + url)
                    .placeholder(R.drawable.movie_image_placeholder)
                    .error(R.drawable.movie_image_placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(view);

        }
    }
}
