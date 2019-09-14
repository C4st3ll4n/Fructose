package com.henrique.fructose.model


import com.google.gson.annotations.SerializedName

data class Cuisine(
    @SerializedName("cuisine_id")
    val cuisineId: String,
    @SerializedName("cuisine_name")
    val cuisineName: String
)