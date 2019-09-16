package com.henrique.fructose.model.restaurant


import com.google.gson.annotations.SerializedName

data class ReviewX(
    @SerializedName("comments_count")
    val commentsCount: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("likes")
    val likes: Int?,
    @SerializedName("rating")
    val rating: Int?,
    @SerializedName("rating_color")
    val ratingColor: String?,
    @SerializedName("rating_text")
    val ratingText: String?,
    @SerializedName("review_text")
    val reviewText: String?,
    @SerializedName("review_time_friendly")
    val reviewTimeFriendly: String?,
    @SerializedName("timestamp")
    val timestamp: Int?,
    @SerializedName("user")
    val user: UserX?
)