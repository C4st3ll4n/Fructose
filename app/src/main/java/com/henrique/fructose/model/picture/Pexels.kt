package com.henrique.fructose.model.picture


import com.google.gson.annotations.SerializedName

data class Pexels(
    @SerializedName("next_page")
    val nextPage: String?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("photos")
    val photos: List<Photo?>?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("url")
    val url: String?
) {
    data class Photo(
        @SerializedName("height")
        val height: Int?,
        @SerializedName("photographer")
        val photographer: String?,
        @SerializedName("src")
        val src: Src?,
        @SerializedName("url")
        val url: String?,
        @SerializedName("width")
        val width: Int?
    ) {
        data class Src(
            @SerializedName("landscape")
            val landscape: String?,
            @SerializedName("large")
            val large: String?,
            @SerializedName("large2x")
            val large2x: String?,
            @SerializedName("medium")
            val medium: String?,
            @SerializedName("original")
            val original: String?,
            @SerializedName("portrait")
            val portrait: String?,
            @SerializedName("small")
            val small: String?,
            @SerializedName("tiny")
            val tiny: String?
        )
    }
}