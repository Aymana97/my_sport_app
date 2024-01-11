package com.example.mysportapp.clients

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ClientInterceptor : Interceptor {

    private val apiKey = "e0499521d2eadba4111cd9dc3a3ab5ac"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request =
            chain.request()
                .newBuilder()
                .addHeader("x-apisports-key", apiKey)
                .build()
        return chain.proceed(request)
    }
}