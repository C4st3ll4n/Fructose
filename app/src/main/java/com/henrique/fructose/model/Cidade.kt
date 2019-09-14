package com.henrique.fructose.model


import com.google.gson.annotations.SerializedName

data class Cidade(
    @SerializedName("country_id")
    val countryId: String,
    @SerializedName("country_name")
    val countryName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_state")
    val isState: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("state_code")
    val stateCode: String,
    @SerializedName("state_id")
    val stateId: String,
    @SerializedName("state_name")
    val stateName: String
)