package com.example.meow.data.repository

import android.util.Log
import com.example.meow.data.local.CatsDatabase
import com.example.meow.data.local.CatsLocalDataSource
import com.example.meow.data.mapper.toBreedInfo
import com.example.meow.data.mapper.toCatsListingEntity
import com.example.meow.data.remote.CatsApi
import com.example.meow.domain.repository.CatsRepository
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
    private val db: CatsDatabase,
    private val catsLocalDataSource: CatsLocalDataSource
) : CatsRepository {
    val dao = db.dao

    override suspend fun getCatsListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<CatsDataState> {
        return flow {
            val localListings = dao.getAllBreeds()
            emit(CatsDataState.Success(localListings.map {
                it.toBreedInfo()
            }))
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val remoteCatsListings = try {
                api.getCatsList()
            } catch (e: IOException) {
                emit(CatsDataState.Unknown)
                null
            } catch (e: HttpException) {
                emit(CatsDataState.Unknown)
                null
            }
            Log.d("CatsRepository", remoteCatsListings.toString())
            remoteCatsListings?.let { catsListing ->
                dao.clearCatsListings()
                dao.insertCatsListings(catsListing.map { it.toCatsListingEntity() })
                emit(CatsDataState.Success(dao.searchCatsListing("").map {
                    it.toBreedInfo()
                }))

            }
        }
    }


}