package com.example.meow.util

import com.example.meow.domain.model.BreedInfo

sealed interface CatsDataState {
    object Loading : CatsDataState
    object Unknown : CatsDataState
    data class Success(val catsList: List<BreedInfo>) : CatsDataState
}