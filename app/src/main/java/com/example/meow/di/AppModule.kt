package com.example.meow.di

import android.app.Application
import androidx.room.Room
import com.example.meow.data.local.CatsDatabase
import com.example.meow.data.remote.CatsApi
import com.example.meow.util.Constants.API_KEY
import com.example.meow.util.Constants.API_KEY_NAME
import com.example.meow.util.Constants.BASE_API
import com.example.meow.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCatsApi(): CatsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(Interceptor { chain ->
                        val builder = chain.request().newBuilder()
                        builder.header(
                            API_KEY_NAME,
                            API_KEY
                        )
                        return@Interceptor chain.proceed(builder.build())
                    }).build()
            )
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideCatsDatabase(app: Application): CatsDatabase {
        return Room.databaseBuilder(
            app,
            CatsDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}