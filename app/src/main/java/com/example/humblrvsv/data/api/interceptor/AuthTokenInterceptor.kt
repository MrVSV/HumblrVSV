package com.example.humblrvsv.data.api.interceptor

import android.util.Log
import com.example.humblrvsv.data.api.interceptor.AuthTokenProvider
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor(private val tokenProvider: AuthTokenProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.e("Kart", "интецептор токен = ${tokenProvider.getToken()}")
        val request = chain.request()
        Log.e("url", chain.request().url.encodedPath)
        if (chain.request().url.encodedPath == "/api/v1/access_token") {
            Log.e("url", "req $request")
            return chain.proceed(request)
        }
        Log.e("url", "else ${chain.request().url.encodedPath}")
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer ${tokenProvider.getToken()}")
            .build()
        Log.e("url", "newReq $newRequest")
        return chain.proceed(newRequest)

    }
//    override fun intercept(chain: Interceptor.Chain): Response {
//        Log.e("Kart", "интецептор токен = ${tokenProvider.getToken()}")
//        val request = chain.request().newBuilder()
//            .addHeader("Authorization", "Bearer ${tokenProvider.getToken()}")
//            .build()
//        return chain.proceed(request)
//    }
}
