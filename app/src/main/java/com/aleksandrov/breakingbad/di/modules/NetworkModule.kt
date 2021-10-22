package com.aleksandrov.breakingbad.di.modules

import com.aleksandrov.breakingbad.data.network.ApiUrls
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS)
            .cookieJar(CookieJar.NO_COOKIES)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiUrls() = ApiUrls

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

}