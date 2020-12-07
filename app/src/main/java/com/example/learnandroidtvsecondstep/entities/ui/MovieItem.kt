package com.example.learnandroidtvsecondstep.entities.ui

data class MovieItem(
    val id: Long,
    val posterPath: String,
    val title: String,
    val overview: String,
    val backdropPath: String
)

fun emptyMovie() : MovieItem = MovieItem(-1, "", "", "", "")