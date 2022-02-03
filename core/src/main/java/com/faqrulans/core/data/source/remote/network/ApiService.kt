package com.faqrulans.core.data.source.remote.network

import com.faqrulans.core.data.source.remote.response.ListDeveloperResponse
import com.faqrulans.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("developers")
    suspend fun getList(
        @Query("key") key: String,
        @Query("page") page: Int
    ): ListDeveloperResponse

    @GET("games")
    suspend fun getGames(
        @Query("key") key: String,
        @Query("developers") developerId: String,
        @Query("page_size") pageSize: Int = 5
    ): ListGameResponse
}
