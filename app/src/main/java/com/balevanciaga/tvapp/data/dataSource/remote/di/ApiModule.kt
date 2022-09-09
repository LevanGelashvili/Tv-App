package com.balevanciaga.tvapp.data.dataSource.remote.di

import androidx.annotation.Nullable
import com.balevanciaga.tvapp.BuildConfig
import com.balevanciaga.tvapp.data.dataSource.remote.RequestInterceptor
import com.balevanciaga.tvapp.data.dataSource.remote.api.ApiEndpoints
import com.balevanciaga.tvapp.data.dataSource.remote.api.TvShowApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    @OkHttpRequestInterceptor
    fun provideRequestInterceptor(): Interceptor = RequestInterceptor()

    @Provides
    @Singleton
    @OkHttpLoggingInterceptor
    @Nullable
    fun provideLoggingInterceptor(): Interceptor? =
        if (BuildConfig.DEBUG)
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            } else null

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @OkHttpLoggingInterceptor loggingInterceptor: Interceptor?,
        @OkHttpRequestInterceptor requestInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient
        .Builder()
        .apply {
            loggingInterceptor?.let { interceptor -> addInterceptor(interceptor) }
        }
        .addInterceptor(requestInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit.Builder =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(ApiEndpoints.BASE_URL)
            .client(okHttpClient)

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): TvShowApi =
        builder
            .build()
            .create(TvShowApi::class.java)

    @Qualifier
    annotation class OkHttpLoggingInterceptor

    @Qualifier
    annotation class OkHttpRequestInterceptor
}