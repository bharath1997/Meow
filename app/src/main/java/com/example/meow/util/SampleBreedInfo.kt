package com.example.meow.util

import com.example.meow.data.local.Ratings
import com.example.meow.domain.model.BreedInfo
import com.example.meow.domain.model.RatingsUI

/**
 * Created by Bharath on 10/31/2022.
 */
val sampleBreedInfo = BreedInfo(
    id = "5",
    breedName = "Test",
    imageUrl = "https://cdn2.thecatapi.com/images/KWdLHmOqc.jpg",
    origin = "India",
    nature = "Test",
    description = "Test shdjhsjdhf shdfkjshdkfjsh hskjdhfkjsh",
    lifeSpan = "10",
    alternativeNames = "Test test, sets",
    ratings = listOf(
        RatingsUI("Indoor", 0.8f),
        RatingsUI("lap", 0.2f),
        RatingsUI("Adaptability", 0f),
        RatingsUI("Affection Level", 0.9f),
        RatingsUI("Child Friendly", 0.3f),
        RatingsUI("Dog Friendly", 0f)
        )
)