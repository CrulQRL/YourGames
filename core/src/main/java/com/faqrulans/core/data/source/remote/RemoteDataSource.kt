package com.faqrulans.core.data.source.remote

import android.util.Log
import com.faqrulans.core.BuildConfig
import com.faqrulans.core.data.source.remote.network.ApiResponse
import com.faqrulans.core.data.source.remote.network.ApiService
import com.faqrulans.core.data.source.remote.response.ListDeveloperResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllDeveloper(): Flow<ApiResponse<ListDeveloperResponse>> {
        return flow {
            try {
                val response = apiService.getList(BuildConfig.API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}
