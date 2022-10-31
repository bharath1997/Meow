package com.example.meow.di

import com.example.meow.data.repository.CatsRepositoryImpl
import com.example.meow.domain.repository.CatsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Bharath on 10/28/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCatsRepository(catsRepositoryImpl: CatsRepositoryImpl): CatsRepository
}