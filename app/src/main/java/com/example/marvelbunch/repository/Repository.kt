package com.example.marvelbunch.repository

import com.example.marvelbunch.network.ApiService

class Repository(private val apiService: ApiService) {
    suspend fun getComics(apiKey: String, ts: String, hash: String) = apiService.fetchComics(apiKey, ts, hash)
    suspend fun getCharacters(apiKey: String, ts: String, hash: String, offset: Int, limit: Int) = apiService.fetchCharacters(apiKey, ts, hash, offset, limit)
    suspend fun getEvents(apiKey: String, ts: String, hash: String) = apiService.fetchEvents(apiKey, ts, hash)
}