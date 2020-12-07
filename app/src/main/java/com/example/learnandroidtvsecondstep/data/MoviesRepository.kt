package com.example.learnandroidtvsecondstep.data

import com.example.learnandroidtvsecondstep.entities.ui.MoviesData
import com.example.learnandroidtvsecondstep.result.GenericError
import com.example.learnandroidtvsecondstep.result.Result

interface MoviesRepository {

    suspend fun fetchPopularMovies(page: Int): Result<GenericError, MoviesData>

    suspend fun fetchNowPlayingMovies(page: Int): Result<GenericError, MoviesData>

    suspend fun fetchTopRatedMovies(page: Int): Result<GenericError, MoviesData>

    suspend fun fetchUpcomingMoviesMovies(page: Int): Result<GenericError, MoviesData>
}