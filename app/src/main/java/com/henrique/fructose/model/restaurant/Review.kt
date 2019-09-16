package com.henrique.fructose.model.restaurant


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Review(
    @SerializedName("review")
    val review: ReviewX?
): Serializable