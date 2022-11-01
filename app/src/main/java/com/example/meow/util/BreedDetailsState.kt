package com.example.meow.util

import com.example.meow.domain.model.BreedInfo

sealed interface BreedDetailsState {
    object Loading : BreedDetailsState
    object Unknown : BreedDetailsState
    data class Success(val breedInfo: BreedInfo) : BreedDetailsState
}