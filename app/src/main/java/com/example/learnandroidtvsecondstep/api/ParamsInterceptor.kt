package com.example.learnandroidtvsecondstep.api

import com.example.learnandroidtvsecondstep.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ParamsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val newUrl = req
            .url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .addQueryParameter("language", "ru-Ru")
            .build()
        req = req.newBuilder().url(newUrl).build()
        return chain.proceed(req)
    }
}