package com.example.learnandroidtvsecondstep.entities

import com.example.learnandroidtvsecondstep.entities.dto.MovieDto
import com.example.learnandroidtvsecondstep.entities.dto.MovieResponseDto
import com.example.learnandroidtvsecondstep.entities.ui.MovieItem
import com.example.learnandroidtvsecondstep.entities.ui.MoviesData

fun MovieResponseDto.toDomain(): MoviesData = MoviesData(
        page = page ?: 0,
        totalResults = totalResults ?: 0,
        totalPages = totalPages ?: 0,
        results = results?.toDomain() ?: emptyList()
)

fun MovieDto.toDomain(): MovieItem = MovieItem(
        id = id,
        posterPath = posterPath ?: "",
        title = title ?: "",
        overview = overview ?: "",
        backdropPath = backdropPath ?: ""
)

fun List<MovieDto>.toDomain(): List<MovieItem> {
    return map { it.toDomain() }
}