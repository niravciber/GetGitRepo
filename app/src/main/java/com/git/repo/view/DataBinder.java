package com.git.repo.view;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public final class DataBinder {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageview , String url){
        Context context = imageview.getContext();
        Glide.with(context).load(url).into(imageview);
    }
}
