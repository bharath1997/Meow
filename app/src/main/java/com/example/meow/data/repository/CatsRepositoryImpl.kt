package com.example.meow.data.repository

import com.example.meow.data.local.CatsDatabase
import com.example.meow.data.mapper.toBreedInfo
import com.example.meow.data.mapper.toCatsListingEntity
import com.example.meow.data.remote.CatsApi
import com.example.meow.domain.repository.CatsRepository
import com.example.meow.util.BreedDetailsState
import com.example.meow.util.CatsDataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Bharath on 10/28/2022.
 */

@Singleton
class CatsRepositoryImpl @Inject constructor(
    private val api: CatsApi,
    db: CatsDatabase,
) : CatsRepository {
    private val dao = db.dao

    override suspend fun getCatsListing(
        query: String
    ): Flow<CatsDataState> {
        return flow {
            val localListings = dao.getAllBreeds()
            val isDbEmpty = localListings.isEmpty()
            if (!isDbEmpty) {
                emit(CatsDataState.Success(localListings.map {
                    it.toBreedInfo()
                }))
            }
            val remoteCatsListings = try {
                api.getCatsList()
            } catch (e: IOException) {
                    if (isDbEmpty) emit(CatsDataState.Unknown)
                null
            } catch (e: HttpException) {
                if (isDbEmpty) emit(CatsDataState.Unknown)
                null
            }
            remoteCatsListings?.let { catsListing ->
                dao.clearCatsListings()
                dao.insertCatsListings(catsListing.map { it.toCatsListingEntity() })
                emit(CatsDataState.Success(dao.getAllBreeds().map {
                    it.toBreedInfo()
                }))

            }
        }
    }

    override suspend fun getBreed(id: String): Flow<BreedDetailsState> {
        return flow {
            val breedDetails = dao.getBreed(id).toBreedInfo()
            if (breedDetails.id.isNotEmpty()) {
                emit(BreedDetailsState.Success(breedDetails))
            } else {
                emit(BreedDetailsState.Unknown)
            }
        }
    }


}