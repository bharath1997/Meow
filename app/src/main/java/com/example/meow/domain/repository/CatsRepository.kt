package com.example.meow.domain.repository

import com.example.meow.util.BreedDetailsState
import com.example.meow.util.CatsDataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bharath on 10/28/2022.
 */
interface CatsRepository {


    suspend fun getCatsListing(query: String = ""): Flow<CatsDataState>
    suspend fun getBreed(id: String): Flow<BreedDetailsState>
}