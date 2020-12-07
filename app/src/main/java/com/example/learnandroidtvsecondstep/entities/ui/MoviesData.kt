package com.example.learnandroidtvsecondstep.entities.ui

data class MoviesData(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<MovieItem>
)

fun emptyMoviesData() : MoviesData = MoviesData(0, 0, 0, emptyList())