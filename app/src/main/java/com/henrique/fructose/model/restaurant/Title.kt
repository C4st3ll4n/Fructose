package com.henrique.fructose.model.restaurant


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Title(
    @SerializedName("text")
    val text: String?
): Serializable