package com.example.meow.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Bharath on 10/29/2022.
 */

@Dao
interface CatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatsListings(
        catsListingEntity: List<CatsListingEntity>
    )

    @Query("DELETE FROM catslistingentity")
    suspend fun clearCatsListings()

    @Query("SELECT * FROM catslistingentity")
    suspend fun getAllBreeds():List<CatsListingEntity>

    @Query(
        """
            SELECT * FROM catslistingentity
            WHERE LOWER(breedName) LIKE '%' || LOWER(:query) || '%'
        """
    )
    suspend fun searchCatsListing(query: String): List<CatsListingEntity>
}