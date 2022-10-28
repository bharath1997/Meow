package com.example.meow.di

import android.app.Application
import androidx.room.Room
import com.example.meow.data.local.CatsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    fun provideCatApi():

    @Provides
    @Singleton
    fun provideCatsDatabase(app: Application): CatsDatabase {
        return Room.databaseBuilder(
            app,
            CatsDatabase::class.java,
            "catsdb.db"
        ).build()
    }

}