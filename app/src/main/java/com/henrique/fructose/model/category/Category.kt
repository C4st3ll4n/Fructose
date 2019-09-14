package com.henrique.fructose.model.category

import com.google.gson.annotations.SerializedName

data class Category(
        @SerializedName("categories")
        val categories: CategoryInfo
    )