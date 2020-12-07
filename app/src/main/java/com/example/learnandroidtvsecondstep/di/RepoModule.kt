package com.example.learnandroidtvsecondstep.di

import com.example.learnandroidtvsecondstep.api.AppApi
import com.example.learnandroidtvsecondstep.data.MoviesRepository
import com.example.learnandroidtvsecondstep.data.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun providePopularMovieRepository(api: AppApi): MoviesRepository = MoviesRepositoryImpl(api)
}