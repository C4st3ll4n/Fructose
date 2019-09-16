package com.henrique.fructose.model.restaurant


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class R(
    @SerializedName("has_menu_status")
    val hasMenuStatus: HasMenuStatus?,
    @SerializedName("res_id")
    val resId: Int?
): Serializable