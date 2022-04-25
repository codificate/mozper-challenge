package com.challenge.mozper.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.challenge.mozper.R

fun downloadImage(url: String, context: Context) = Glide.with(context).load(url).error(R.drawable.mozper_logo).centerCrop()

fun downloadImageRoundCorners(radius: Int, url: String, context: Context): RequestBuilder<Drawable> {
    return Glide.with(context).load(url).error(R.drawable.mozper_logo)
        .apply{
            this.transform(*mutableListOf<Transformation<Bitmap>>(CenterCrop(), RoundedCorners(radius)).toTypedArray())
        }
}