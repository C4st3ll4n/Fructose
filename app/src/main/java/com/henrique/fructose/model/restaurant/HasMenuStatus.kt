package com.henrique.fructose.model.restaurant


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HasMenuStatus(
    @SerializedName("delivery")
    val delivery: Int?,
    @SerializedName("takeaway")
    val takeaway: Int?
): Serializable