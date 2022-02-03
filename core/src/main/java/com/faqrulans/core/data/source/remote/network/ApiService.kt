package com.faqrulans.core.data.source.remote.network

import com.faqrulans.core.data.source.remote.response.ListDeveloperResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("developers")
    suspend fun getList(
        @Query("key") key: String,
        @Query("page") page: Int
    ): ListDeveloperResponse

    @GET("games/{id}")
    suspend fun getGameDetail(
        @Query("key") key: String,
        @Path(value = "id") id: Long,
    ): ListDeveloperResponse
}
