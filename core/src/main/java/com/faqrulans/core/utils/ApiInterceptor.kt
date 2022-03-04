package com.faqrulans.core.utils

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("key", KeysManager.getApiKey())
            .build()

        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}
