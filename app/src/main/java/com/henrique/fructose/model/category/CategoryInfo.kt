package com.henrique.fructose.model.category

import com.google.gson.annotations.SerializedName

data class CategoryInfo(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
)