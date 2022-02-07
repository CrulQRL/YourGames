package com.faqrulans.core.data.source.remote

import android.util.Log
import com.faqrulans.core.BuildConfig
import com.faqrulans.core.data.source.remote.network.ApiResponse
import com.faqrulans.core.data.source.remote.network.ApiService
import com.faqrulans.core.data.source.remote.response.DeveloperResponse
import com.faqrulans.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllDeveloper(): Flow<ApiResponse<List<DeveloperResponse>>> {
        return flow {
            val developerResponse = mutableListOf<DeveloperResponse>()
            val responsePage1 = apiService.getList(BuildConfig.API_KEY, 1)
            val responsePage2 = apiService.getList(BuildConfig.API_KEY, 2)
            val responsePage3 = apiService.getList(BuildConfig.API_KEY, 3)

            developerResponse.addAll(responsePage1.results)
            developerResponse.addAll(responsePage2.results)
            developerResponse.addAll(responsePage3.results)

            if (developerResponse.isNotEmpty()){
                emit(ApiResponse.Success(developerResponse))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch {
            emit(ApiResponse.Error(it.toString()))
            Log.e("RemoteDataSource", it.toString())
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGames(developerId: String): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            val response = apiService.getGames(BuildConfig.API_KEY, developerId)
            val games = response.results

            if (games.isNotEmpty()){
                emit(ApiResponse.Success(games))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch {
            emit(ApiResponse.Error(it.toString()))
            Log.e("RemoteDataSource", it.toString())
        }.flowOn(Dispatchers.IO)
    }

}
