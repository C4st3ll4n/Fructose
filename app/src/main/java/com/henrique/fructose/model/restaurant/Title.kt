package com.henrique.fructose.model.restaurant


import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("text")
    val text: String?
)