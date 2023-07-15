package com.example.maplibrebarikoi.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Places(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("distance_in_meters") var distanceInMeters: Double? = null,
    @SerializedName("longitude") var longitude: Double? = null,
    @SerializedName("latitude") var latitude: Double? = null,
    @SerializedName("Address") var Address: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("area") var area: String? = null,
    @SerializedName("pType") var pType: String? = null,
    @SerializedName("subType") var subType: String? = null,
    @SerializedName("uCode") var uCode: String? = null,
    @SerializedName("ST_AsText(location)") var STAsTextlocation: String? = null,
    @SerializedName("contact_person_phone") var contactPersonPhone: String? = null
) : Serializable

