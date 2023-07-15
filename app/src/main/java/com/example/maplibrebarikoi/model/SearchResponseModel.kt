package com.example.maplibrebarikoi.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchResponseModel(
    @SerializedName("places" ) var places : ArrayList<Places> = arrayListOf()
) : Serializable

