package com.henrique.fructose.model.restaurant


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Photo(
    @SerializedName("photo")
    val photo: PhotoX?
): Serializable