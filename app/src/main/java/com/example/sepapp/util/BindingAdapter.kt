package com.example.sepapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bindImageUrl")
fun loadImage(view: ImageView, imageUrl:String){
    Glide.with(view.context).
            load(imageUrl).
            into(view)
}

