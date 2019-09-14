package com.henrique.fructose.model


import com.google.gson.annotations.SerializedName

data class Estabelecimento(
    @SerializedName("establishment_id")
    val establishmentId: String,
    @SerializedName("establishment_name")
    val establishmentName: String
)