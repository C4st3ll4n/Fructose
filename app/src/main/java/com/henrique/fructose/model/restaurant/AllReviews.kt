package com.henrique.fructose.model.restaurant


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AllReviews(
    @SerializedName("reviews")
    val reviews: List<Review?>?
): Serializable