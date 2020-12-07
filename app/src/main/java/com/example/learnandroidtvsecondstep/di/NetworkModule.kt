package com.example.learnandroidtvsecondstep.di

import com.example.learnandroidtvsecondstep.BuildConfig
import com.example.learnandroidtvsecondstep.api.AppApi
import com.example.learnandroidtvsecondstep.api.ParamsInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideApi(): AppApi {
        val retrofit = initRetrofit()
        return retrofit.create(AppApi::class.java)
    }

    private fun initRetrofit(): Retrofit {
        val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(initConverterFactory())
            .client(initOkHttpClient())
        return retrofitBuilder.build()
    }

    private fun initConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    private fun initOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logging)
        builder.addInterceptor(ParamsInterceptor())
        return builder.build()
    }
}