package com.henrique.fructose.model.colection


import com.google.gson.annotations.SerializedName

data class Collection(
    @SerializedName("collection")
    val collection: CollectionX?
)