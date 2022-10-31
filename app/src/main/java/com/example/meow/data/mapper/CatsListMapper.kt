package com.example.meow.data.mapper

import com.example.meow.data.local.CatsListingEntity
import com.example.meow.data.local.Ratings
import com.example.meow.data.remote.dto.CatBreedsResponseItem
import com.example.meow.domain.model.BreedInfo

/**
 * Created by Bharath on 10/29/2022.
 */

fun CatBreedsResponseItem.toCatsListingEntity(): CatsListingEntity {
    val petBreed = if (!breeds.isNullOrEmpty()) {
        breeds[0]
    } else {
        null
    }
    return CatsListingEntity(
        breedName = petBreed?.name ?: "",
        id = id,
        imageUrl = url,
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

fun CatsListingEntity.toBreedInfo(): BreedInfo {
    return BreedInfo(
        breedName = breedName,
        imageUrl = imageUrl,
        origin = origin,
        nature = nature,
        description = description,
        lifeSpan = lifeSpan,
        alternativeNames = alternativeNames,
        ratings = ratings

    )
}