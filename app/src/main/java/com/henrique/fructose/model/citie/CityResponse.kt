package com.henrique.fructose.model.citie


import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("has_more")
    val hasMore: Int?,
    @SerializedName("has_total")
    val hasTotal: Int?,
    @SerializedName("location_suggestions")
    val locationSuggestions: List<LocationSuggestion?>?,
    @SerializedName("status")
    val status: String?
)