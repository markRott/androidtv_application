package com.example.learnandroidtvsecondstep.utils

import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.Presenter

class PaginationAdapter(presenter: Presenter) : ArrayObjectAdapter(presenter) {

    var page : Int  = 1
    var totalPages : Int = 1

    fun hasMoreData() : Boolean = page < totalPages
}