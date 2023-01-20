package com.example.humblrvsv.di

import android.content.Context
import com.example.humblrvsv.data.api.*
import com.example.humblrvsv.data.api.dto.ThingDto
import com.example.humblrvsv.data.api.dto.commentdto.CommentDto
import com.example.humblrvsv.data.api.dto.linkdto.PostDto
import com.example.humblrvsv.data.api.interceptor.AuthTokenInterceptor
import com.example.humblrvsv.data.api.interceptor.AuthTokenInterceptorQualifier
import com.example.humblrvsv.data.api.interceptor.AuthTokenProvider
import com.example.humblrvsv.data.api.interceptor.LoggingInterceptorQualifier
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideAuthTokenProvider(@ApplicationContext context: Context): AuthTokenProvider =
        AuthTokenProvider(context)

    @Provides
    @AuthTokenInterceptorQualifier
    fun provideAuthTokenInterceptor(tokenProvider: AuthTokenProvider): Interceptor =
        AuthTokenInterceptor(tokenProvider)

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
    @Named("Normal")
    fun provideRetrofit(okhttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://oauth.reddit.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okhttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("Converted")
    fun provideRetrofitConverted(okhttpClient: OkHttpClient): Retrofit {
        val moshiBuilder = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(RepliesAdapter())
            .add(
                PolymorphicJsonAdapterFactory.of(ThingDto::class.java, "kind")
                    .withSubtype(PostDto::class.java, "t3")
                    .withSubtype(CommentDto::class.java, "t1")
            )
            .build()
        return Retrofit.Builder()
            .baseUrl("https://oauth.reddit.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshiBuilder))
            .client(okhttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiToken(@Named("Normal") retrofit: Retrofit): ApiToken =
        retrofit.create(ApiToken::class.java)

    @Provides
    @Singleton
    fun provideApiProfile(@Named("Normal") retrofit: Retrofit): ApiProfile =
        retrofit.create(ApiProfile::class.java)

    @Provides
    @Singleton
    fun provideApiSubreddit(@Named("Normal") retrofit: Retrofit): ApiSubreddit =
        retrofit.create(ApiSubreddit::class.java)

    @Provides
    @Singleton
    fun provideApiPost(@Named("Normal") retrofit: Retrofit): ApiPost =
        retrofit.create(ApiPost::class.java)

    @Provides
    @Singleton
    fun provideApiSinglePost(@Named("Converted") retrofit: Retrofit): ApiSinglePost =
        retrofit.create(ApiSinglePost::class.java)

}