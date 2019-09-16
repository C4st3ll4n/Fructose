package com.henrique.fructose.model.restaurant


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RatingObj(
    @SerializedName("bg_color")
    val bgColor: BgColor?,
    @SerializedName("title")
    val title: Title?
): Serializable