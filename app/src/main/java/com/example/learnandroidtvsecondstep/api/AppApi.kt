package com.example.learnandroidtvsecondstep.api

import com.example.learnandroidtvsecondstep.entities.dto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {

    @GET("/3/movie/popular")
    suspend fun fetchPopularMovie(@Query("page") page: Int): MovieResponseDto

    @GET("/3/movie/now_playing")
    suspend fun fetchNowPlayingMovies(@Query("page") page: Int): MovieResponseDto

    @GET("/3/movie/top_rated")
    suspend fun fetchTopRatedMovies(@Query("page") page: Int): MovieResponseDto

    @GET("/3/movie/upcoming")
    suspend fun fetchUpcomingMovies(@Query("page") page: Int): MovieResponseDto
}