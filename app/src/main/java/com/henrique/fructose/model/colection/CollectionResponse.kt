package com.henrique.fructose.model.colection


import com.google.gson.annotations.SerializedName

data class CollectionResponse(
    @SerializedName("collections")
    val collections: List<Collection?>?,
    @SerializedName("display_text")
    val displayText: String?,
    @SerializedName("has_more")
    val hasMore: Int?,
    @SerializedName("has_total")
    val hasTotal: Int?,
    @SerializedName("share_url")
    val shareUrl: String?
)