package com.example.humblrvsv.di

import android.content.Context
import com.example.humblrvsv.data.api.ApiLink
import com.example.humblrvsv.data.api.ApiSubreddit
import com.example.humblrvsv.data.api.ApiSaved
import com.example.humblrvsv.data.api.ApiToken
import com.example.humblrvsv.data.api.interceptor.AuthTokenInterceptor
import com.example.humblrvsv.data.api.interceptor.AuthTokenInterceptorQualifier
import com.example.humblrvsv.data.api.interceptor.AuthTokenProvider
import com.example.humblrvsv.data.api.interceptor.LoggingInterceptorQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideAuthTokenProvider(@ApplicationContext context: Context): AuthTokenProvider = AuthTokenProvider(context)

    @Provides
    @AuthTokenInterceptorQualifier
    fun provideAuthTokenInterceptor(tokenProvider: AuthTokenProvider): Interceptor = AuthTokenInterceptor(tokenProvider)

    @Provides
    @LoggingInterceptorQualifier
    fun provideLoginInterceptor(): Interceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideRedditClient(
        @LoggingInterceptorQualifier loggingInterceptor: Interceptor,
        @AuthTokenInterceptorQualifier authTokenInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(loggingInterceptor)
        .addInterceptor(authTokenInterceptor)
        .followRedirects(true)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okhttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://oauth.reddit.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okhttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiToken(retrofit: Retrofit): ApiToken = retrofit.create(ApiToken::class.java)

    @Provides
    @Singleton
    fun provideApiSaved(retrofit: Retrofit): ApiSaved = retrofit.create(ApiSaved::class.java)

    @Provides
    @Singleton
    fun provideApiSubreddit(retrofit: Retrofit): ApiSubreddit = retrofit.create(ApiSubreddit::class.java)

    @Provides
    @Singleton
    fun provideApiLink(retrofit: Retrofit): ApiLink = retrofit.create(ApiLink::class.java)

}