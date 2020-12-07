package com.example.learnandroidtvsecondstep.data

import com.example.learnandroidtvsecondstep.api.AppApi
import com.example.learnandroidtvsecondstep.entities.dto.MovieResponseDto
import com.example.learnandroidtvsecondstep.entities.toDomain
import com.example.learnandroidtvsecondstep.entities.ui.MoviesData
import com.example.learnandroidtvsecondstep.result.ErrorMapper
import com.example.learnandroidtvsecondstep.result.GenericError
import com.example.learnandroidtvsecondstep.result.Result
import com.example.learnandroidtvsecondstep.result.attempt
import com.example.learnandroidtvsecondstep.utils.THREAD_TAG
import com.example.learnandroidtvsecondstep.utils.logDebug
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(private val api: AppApi) : MoviesRepository {

    override suspend fun fetchPopularMovies(page: Int): Result<GenericError, MoviesData> {
        return fetch { api.fetchPopularMovie(page) }
    }

    override suspend fun fetchNowPlayingMovies(page: Int): Result<GenericError, MoviesData> {
        return fetch { api.fetchNowPlayingMovies(page) }
    }

    override suspend fun fetchTopRatedMovies(page: Int): Result<GenericError, MoviesData> {
        return fetch { api.fetchTopRatedMovies(page) }
    }

    override suspend fun fetchUpcomingMoviesMovies(page: Int): Result<GenericError, MoviesData> {
        return fetch { api.fetchUpcomingMovies(page) }
    }

    private suspend fun fetch(action: suspend () -> MovieResponseDto): Result<GenericError, MoviesData> {
        return withContext(Dispatchers.IO) {
            logDebug(THREAD_TAG) { "Repository thread: ${Thread.currentThread().name}" }
            attempt(
                    { ErrorMapper.map(it) },
                    {
                        val response = action.invoke()
                        val result: MoviesData = response.toDomain()
                        result
                    }
            )
        }
    }
}