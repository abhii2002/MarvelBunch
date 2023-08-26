package com.example.marvelbunch.network

import com.example.marvelbunch.models.CharacterResponse
import com.example.marvelbunch.constants.Constants
import com.example.marvelbunch.models.ComicsResponse
import com.example.marvelbunch.models.EventsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


    private val retrofit: Retrofit by lazy {
         Retrofit.Builder()
             .baseUrl(Constants.BASE_URL)
             .addConverterFactory(MoshiConverterFactory.create(moshi))
             .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("comics")
suspend fun fetchComics(@Query("apikey") apiKey: String, @Query("ts") ts: String, @Query("hash") hash: String,): ComicsResponse
    @GET("characters")
    suspend fun fetchCharacters(@Query("apikey") apiKey: String, @Query("ts") ts: String, @Query("hash") hash: String, @Query("offset") offset: Int, @Query("limit") limit: Int): CharacterResponse
    @GET("events")
    suspend fun fetchEvents(@Query("apikey") apiKey: String, @Query("ts") ts: String, @Query("hash") hash: String,): EventsResponse

}