package com.dongchao.core.network.di

import com.blankj.utilcode.util.Utils
import com.dongchao.common.utils.converts.GsonSingleton
import com.dongchao.core.network.RetrofitProcessor
import com.dongchao.core.network.cookie.PersistentCookieJar
import com.dongchao.core.network.cookie.cache.SetCookieCache
import com.dongchao.core.network.cookie.persistence.SharedPrefsCookiePersistor
import com.facebook.stetho.okhttp3.StethoInterceptor

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://www.wanandroid.com/"

//连接时长，单位：秒
private const val CONNECT_TIME_OUT = 10

//同一host并发数
private const val MAX_REQUESTS_PER_HOST = 64

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {

    @Provides
    @Singleton
    fun providerOkHttpClient(): OkHttpClient {
        var app = Utils.getApp()
        var cookieJar = PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(app)
        )

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            ).addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .cookieJar(cookieJar)
            .build()
        okHttpClient.dispatcher.maxRequestsPerHost = MAX_REQUESTS_PER_HOST
        return okHttpClient
    }

    @Provides
    @Singleton
    fun providerRetrofitNetApi(okHttpClient: OkHttpClient): RetrofitProcessor.RetrofitApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonSingleton.GSON))
            .build().create()
    }
}