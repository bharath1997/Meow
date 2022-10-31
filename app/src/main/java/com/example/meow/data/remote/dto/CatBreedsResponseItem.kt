package com.example.meow.data.remote.dto

data class CatBreedsResponseItem(
    val breeds: List<Breed>?,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)