package com.example.learnandroidtvsecondstep.image

import android.app.Activity
import android.widget.ImageView

interface ImageLoaderContract {

    fun loadImage(view: ImageView, url: String)

    fun loadImage(activity: Activity, pathToImage: String)
}