package com.example.learnandroidtvsecondstep.di

import androidx.leanback.widget.Presenter
import com.example.learnandroidtvsecondstep.image.GlideImageLoader
import com.example.learnandroidtvsecondstep.image.ImageLoaderContract
import com.example.learnandroidtvsecondstep.presenters.MovieItemPresenter
import com.example.learnandroidtvsecondstep.utils.ItemsFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppObjectsModule {

    @Provides
    @Singleton
    fun provideImageLoader(): ImageLoaderContract {
        return GlideImageLoader()
    }

    @Provides
    @Singleton
    fun provideHeaderFactory(presenter: Presenter): ItemsFactory {
        return ItemsFactory(presenter)
    }

    @Provides
    @Singleton
    fun providePresenter(imageLoader: ImageLoaderContract): Presenter {
        return MovieItemPresenter(imageLoader)
    }
}