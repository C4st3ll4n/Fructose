package com.henrique.fructose.model.restaurant


import com.google.gson.annotations.SerializedName

data class AllReviews(
    @SerializedName("reviews")
    val reviews: List<Review?>?
)