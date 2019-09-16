package com.henrique.fructose.model.restaurant


import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("photo")
    val photo: PhotoX?
)