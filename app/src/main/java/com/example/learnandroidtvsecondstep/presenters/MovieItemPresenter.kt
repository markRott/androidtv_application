package com.example.learnandroidtvsecondstep.presenters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.Presenter
import com.example.learnandroidtvsecondstep.R
import com.example.learnandroidtvsecondstep.entities.ui.MovieItem
import com.example.learnandroidtvsecondstep.image.ImageLoaderContract
import com.example.learnandroidtvsecondstep.utils.IMAGE_URL
import com.example.learnandroidtvsecondstep.utils.inflate

class MovieItemPresenter(private val imageLoader: ImageLoaderContract) : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = parent?.inflate(R.layout.item_movie)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val rootView: View? = viewHolder?.view
        rootView?.let {
            val tvLabel: TextView = it.findViewById(R.id.tv_label)
            val ivLogo: ImageView = it.findViewById(R.id.iv_logo)
            val movie: MovieItem = item as MovieItem
            tvLabel.text = movie.title
            imageLoader.loadImage(ivLogo, "$IMAGE_URL${movie.posterPath}")
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) = Unit
}