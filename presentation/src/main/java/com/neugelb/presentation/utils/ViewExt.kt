package com.neugelb.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.neugelb.presentation.BuildConfig

fun ImageView.load(url: String?) {
    if (url.isNullOrEmpty()) return

    Glide.with(this)
        .load(BuildConfig.IMAGE_BASE_URL + "$url")
        .into(this)
}