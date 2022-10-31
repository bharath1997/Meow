package com.example.meow.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Bharath on 10/29/2022.
 */
@Entity
data class CatsListingEntity(
    val breedName: String,
    @PrimaryKey val id: String,
    val imageUrl: String,
    val breeId: String,
    val origin: String,
    val nature: String,
    val description: String,
    val lifeSpan: String,
    val alternativeNames: String,
    @Embedded val ratings: Ratings,
)