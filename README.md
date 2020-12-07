**A simple application for displaying a list of movies by category. This application is written for the android tv platform**

Link to youtube: https://youtu.be/qbBc9v4oNS4

---

**To develop this application, I used the following technologies:**

- Coroutines
- ViewModel
- LiveData
- Dagger Hilt
- Glide
- Retrofit
- Gson

---

**To `RUN` a project, you need to get the developer's keys from TMDb servise: https://www.themoviedb.org/login?language=en**


![Add secret key to project](/app/src/main/assets/4.jpg)

---

**MoviesRepositoryImpl class:**

```
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
```


**Images from application:**

![1](/app/src/main/assets/1.jpg)
![2](/app/src/main/assets/2.jpg)
![3](/app/src/main/assets/3.jpg)