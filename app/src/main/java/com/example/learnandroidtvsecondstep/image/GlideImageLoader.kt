package com.example.learnandroidtvsecondstep.image

import android.app.Activity
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.leanback.app.BackgroundManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class GlideImageLoader : ImageLoaderContract {

    override fun loadImage(view: ImageView, url: String) {
        Glide
                .with(view.context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view)
    }

    override fun loadImage(activity: Activity, pathToImage: String) {
        val backgroundManager: BackgroundManager = BackgroundManager.getInstance(activity)
        if (!backgroundManager.isAttached) {
            backgroundManager.attach(activity.window)
        }

        Glide
                .with(activity)
                .asDrawable()
                .load(pathToImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        backgroundManager.drawable = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })
    }
}