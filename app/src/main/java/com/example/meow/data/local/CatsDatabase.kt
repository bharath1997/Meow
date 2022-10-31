package com.example.meow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Bharath on 10/28/2022.
 */
@Database(
    entities = [CatsListingEntity::class],
    version = 1
)
abstract class CatsDatabase : RoomDatabase() {
    abstract val dao: CatsDao
}