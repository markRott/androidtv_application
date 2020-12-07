package com.example.learnandroidtvsecondstep.ui

import com.example.learnandroidtvsecondstep.utils.PaginationAdapter

class MovieRow(
        val id: Long,
        val title: String,
        var page: Int = 1,
        val adapter: PaginationAdapter
)