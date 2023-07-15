package com.example.maplibrebarikoi.network


import com.example.maplibrebarikoi.utils.AppConstants
import com.example.maplibrebarikoi.model.SearchResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//|------------------|INTERFACE - RETROFIT API|------------------|
interface RetrofitApi {

    @GET("api/search/nearby/category/{API_KEY}/{distance}/{limit}")
    fun getNearbyBanks(
        @Path("API_KEY") API_KEY: String,
        @Path("distance") distance: Int,
        @Path("limit") limit: Int,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("ptype") ptype: String,
    ): Call<SearchResponseModel>
}