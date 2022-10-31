package com.example.meow.domain.model

import com.example.meow.data.local.Ratings

data class BreedInfo(
    val breedName: String,
    val imageUrl: String,
    val origin: String,
    val nature: String,
    val description: String,
    val lifeSpan: String,
    val alternativeNames: String,
    val ratings: Ratings,
)
