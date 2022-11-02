package com.example.meow.data.mapper

import com.example.meow.data.local.CatsListingEntity
import com.example.meow.data.local.Ratings
import com.example.meow.data.remote.dto.CatBreedsResponseItem
import com.example.meow.domain.model.BreedInfo
import com.example.meow.domain.model.RatingsUI

/**
 * Created by Bharath on 10/29/2022.
 */

fun CatBreedsResponseItem.toCatsListingEntity(): CatsListingEntity {
    val tempId = 999
    val petBreed = if (!breeds.isNullOrEmpty()) {
        breeds[0]
    } else {
        null
    }
    return CatsListingEntity(
        breedName = petBreed?.name ?: "",
        id = id ?: (tempId.toString()).also { tempId + 1 },
        imageUrl = url ?: "",
        breeId = petBreed?.id ?: "",
        origin = petBreed?.origin ?: "",
        nature = petBreed?.temperament ?: "",
        description = petBreed?.description ?: "",
        lifeSpan = petBreed?.life_span ?: "",
        alternativeNames = petBreed?.alt_names ?: "",
        ratings = Ratings(
            indoor = petBreed?.indoor ?: 0,
            lap = petBreed?.lap ?: 0,
            adaptability = petBreed?.adaptability ?: 0,
            affectionLevel = petBreed?.affection_level ?: 0,
            childFriendly = petBreed?.child_friendly ?: 0,
            dogFriendly = petBreed?.dog_friendly ?: 0,
            energyLevel = petBreed?.energy_level ?: 0,
            grooming = petBreed?.grooming ?: 0,
            healthIssues = petBreed?.health_issues ?: 0,
            intelligence = petBreed?.intelligence ?: 0,
            sheddingLevel = petBreed?.shedding_level ?: 0,
            socialNeeds = petBreed?.social_needs ?: 0,
            strangerFriendly = petBreed?.stranger_friendly ?: 0,
            vocalization = petBreed?.vocalisation ?: 0,
            experimental = petBreed?.experimental ?: 0,
            natural = petBreed?.natural ?: 0,
            rare = petBreed?.rare ?: 0
        )
    )
}

val ratingsList = fun(ratings: Ratings) = listOf(
    RatingsUI("Indoor", (ratings.indoor * 20).div(100f)),
    RatingsUI("lap", (ratings.lap * 20).div(100f)),
    RatingsUI("Adaptability", (ratings.adaptability * 20).div(100f)),
    RatingsUI("Affection Level", (ratings.affectionLevel * 20).div(100f)),
    RatingsUI("Child Friendly", (ratings.childFriendly * 20).div(100f)),
    RatingsUI("Dog Friendly", (ratings.dogFriendly * 20).div(100f)),
    RatingsUI("Energy Level", (ratings.energyLevel * 20).div(100f)),
    RatingsUI("Grooming", (ratings.grooming * 20).div(100f)),
    RatingsUI("Health Issues", (ratings.healthIssues * 20).div(100f)),
    RatingsUI("Intelligence", (ratings.intelligence * 20).div(100f)),
    RatingsUI("Shedding Level", (ratings.sheddingLevel * 20).div(100f)),
    RatingsUI("Social Needs", (ratings.socialNeeds * 20).div(100f)),
    RatingsUI("Stranger Friendly", (ratings.strangerFriendly * 20).div(100f)),
    RatingsUI("Vocalization", (ratings.vocalization * 20).div(100f)),
    RatingsUI("Experimental", (ratings.experimental * 20).div(100f)),
    RatingsUI("Natural", (ratings.natural * 20).div(100f)),
    RatingsUI("Rare", (ratings.rare * 20).div(100f))
)

fun CatsListingEntity.toBreedInfo(): BreedInfo {


    return BreedInfo(
        id = id,
        breedName = breedName,
        imageUrl = imageUrl,
        origin = origin,
        nature = nature,
        description = description,
        lifeSpan = lifeSpan,
        alternativeNames = alternativeNames,
        ratings = ratingsList(ratings).sortedByDescending { it.percentage }

    )
}