package com.example.meow.data.remote

import com.example.meow.data.remote.dto.CatBreedsResponse
import com.example.meow.util.Constants.API_KEY
import com.example.meow.util.Constants.QUERY_PAGE_SIZE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by Bharath on 10/29/2022.
 */
interface CatsApi {

    @GET("v1/images/search")
    suspend fun getCatsList(
        @Query("order")
        order: String = "RANDOM",
        @Query("page")
        page: Int = 0,
        @Query("limit")
        limit: Int = QUERY_PAGE_SIZE,
        @Query("has_breeds")
        hasBreed: Boolean = true
    ): CatBreedsResponse

}