package com.example.learnandroidtvsecondstep.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnandroidtvsecondstep.data.MoviesRepository
import com.example.learnandroidtvsecondstep.entities.ui.MoviesData
import com.example.learnandroidtvsecondstep.result.GenericError
import com.example.learnandroidtvsecondstep.result.Result
import com.example.learnandroidtvsecondstep.result.fold
import com.example.learnandroidtvsecondstep.utils.*
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
        private val repository: MoviesRepository
) : ViewModel() {

    private val _moviesMLD = MutableLiveData<MovieState>()
    val moviesLD: LiveData<MovieState> get() = _moviesMLD

    fun fetchAllCategories(page: Int = 1) {
        fetchPopularMovie(page)
        fetchNowPlayingMovies(page)
        fetchTopRatedMovies(page)
        fetchUpcomingMovies(page)
    }

    private fun fetchPopularMovie(page: Int) {
        fetch(POPULAR) { repository.fetchPopularMovies(page) }
    }

    private fun fetchNowPlayingMovies(page: Int) {
        fetch(NOW_PLAYING) { repository.fetchNowPlayingMovies(page) }
    }

    private fun fetchTopRatedMovies(page: Int) {
        fetch(TOP_RATED) { repository.fetchTopRatedMovies(page) }
    }

    private fun fetchUpcomingMovies(page: Int) {
        fetch(UPCOMING) { repository.fetchUpcomingMoviesMovies(page) }
    }

    private fun fetch(id: Int, action: suspend () -> Result<GenericError, MoviesData>) {
        viewModelScope.launch {
            action.invoke().fold(
                    ifSuccess = { setupData(id, it) },
                    ifError = {
                        /**
                         * At this point, you can notify your view of the error that has occurred.
                         */
                        logDebug(APP_TAG) { "MainViewModel error: $it" }
                    }
            )
        }
    }

    private fun setupData(id: Int, data: MoviesData) {
        _moviesMLD.value = MovieState(id, data)
        logDebug(THREAD_TAG) { "MainViewModel Thread: ${Thread.currentThread().name}" }
    }

    /**
     * This method is required to load data page by page.
     * The implementation of this logic will be written later.
     */
    fun fetchById(id: Int, page: Int) {
        when (id) {
            POPULAR -> fetchPopularMovie(page)
            NOW_PLAYING -> fetchNowPlayingMovies(page)
            TOP_RATED -> fetchTopRatedMovies(page)
            UPCOMING -> fetchUpcomingMovies(page)
        }
    }
}