package com.faqrulans.core.di

import com.faqrulans.core.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
interface NetworkModule {

    companion object {

        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            val hostname = "api.rawg.io"
            val certificatePinner = CertificatePinner.Builder()
                .add(hostname, "sha256/RI9CUmPUOpUk2vdVMSZDWj+wtoQO5k9MSCSM9w4grmU=")
                .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
                .add(hostname, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
                .build()

            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(timeout = 120, unit = TimeUnit.SECONDS)
                .readTimeout(timeout = 120, unit = TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
                .build()
        }

        @Singleton
        @Provides
        fun provideApiService(client: OkHttpClient): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.rawg.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }


    }

}
