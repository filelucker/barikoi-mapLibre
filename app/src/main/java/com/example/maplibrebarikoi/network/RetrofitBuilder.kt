package com.example.maplibrebarikoi.network

import com.example.maplibrebarikoi.utils.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//|------------------|CLASS - RETROFIT BUILDER|------------------|
class RetrofitBuilder {
    //|-------------------|METHOD - BUILD API|-------------------|
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(AppConstants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private val okHttpClient by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        okHttpClient
    }
}