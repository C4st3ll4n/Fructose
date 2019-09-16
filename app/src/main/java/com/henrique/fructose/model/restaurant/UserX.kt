package com.henrique.fructose.model.restaurant


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserX(
    @SerializedName("foodie_color")
    val foodieColor: String?,
    @SerializedName("foodie_level")
    val foodieLevel: String?,
    @SerializedName("foodie_level_num")
    val foodieLevelNum: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("profile_deeplink")
    val profileDeeplink: String?,
    @SerializedName("profile_image")
    val profileImage: String?,
    @SerializedName("profile_url")
    val profileUrl: String?
): Serializable