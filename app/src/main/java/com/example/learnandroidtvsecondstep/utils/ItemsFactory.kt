package com.example.learnandroidtvsecondstep.utils

import androidx.collection.SparseArrayCompat
import androidx.collection.forEach
import androidx.leanback.widget.*
import com.example.learnandroidtvsecondstep.ui.MovieRow

class ItemsFactory(private val movieItemPresenter: Presenter) {

    fun prepareLeftRows(movieRowsMap: SparseArrayCompat<MovieRow>, mainAdapter: ArrayObjectAdapter) {

        movieRowsMap.put(POPULAR,
                MovieRow(
                        POPULAR.toLong(),
                        "Popular",
                        adapter = PaginationAdapter(movieItemPresenter))
        )

        movieRowsMap.put(NOW_PLAYING,
                MovieRow(
                        NOW_PLAYING.toLong(),
                        "Now Playing",
                        adapter = PaginationAdapter(movieItemPresenter))
        )

        movieRowsMap.put(TOP_RATED,
                MovieRow(
                        TOP_RATED.toLong(),
                        "Top Rated",
                        adapter = PaginationAdapter(movieItemPresenter))
        )

        movieRowsMap.put(UPCOMING,
                MovieRow(
                        UPCOMING.toLong(),
                        "Upcoming",
                        adapter = PaginationAdapter(movieItemPresenter))
        )

        movieRowsMap.forEach { _, value ->
            val headerItem = HeaderItem(value.id, value.title)
            val listRow = ListRow(headerItem, value.adapter)
            mainAdapter.add(listRow)
        }
    }
}