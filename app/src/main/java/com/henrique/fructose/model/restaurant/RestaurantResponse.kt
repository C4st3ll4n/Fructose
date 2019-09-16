package com.henrique.fructose.model.restaurant


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RestaurantResponse(
    @SerializedName("restaurants")
    val restaurants: List<Restaurant?>?,
    @SerializedName("results_found")
    val resultsFound: Int?,
    @SerializedName("results_shown")
    val resultsShown: Int?,
    @SerializedName("results_start")
    val resultsStart: Int?
): Serializable