package com.henrique.fructose.model.restaurant


import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("review")
    val review: ReviewX?
)