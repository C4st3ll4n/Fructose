package com.henrique.fructose.model.establishment


import com.google.gson.annotations.SerializedName

data class EstablishmentResponse(
    @SerializedName("establishments")
    val establishments: List<Establishment?>?
) {
    data class Establishment(
        @SerializedName("establishment")
        val establishment: Establishment?
    ) {
        data class Establishment(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("name")
            val name: String?
        )
    }
}