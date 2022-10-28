package com.example.meow.data.dto

import com.google.gson.annotations.SerializedName

data class CatResponse(

    @SerializedName("breeds")
    val petBreeds: List<Breed>?,

    @SerializedName("categories")
    val petCategories: List<Category>?,

    @SerializedName("id")
    val id: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("width")
    val imageWidth: Int,

    @SerializedName("height")
    val imageHeight: Int
)