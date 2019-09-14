package com.henrique.fructose.model


import com.google.gson.annotations.SerializedName

data class Collections(
    @SerializedName("collection_id")
    val collectionId: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("res_count")
    val resCount: String,
    @SerializedName("share_url")
    val shareUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)