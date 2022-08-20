package com.dongchao.core.network.di

import com.blankj.utilcode.util.Utils
import com.dongchao.core.network.RetrofitProcessor
import com.dongchao.core.network.cookie.PersistentCookieJar
import com.dongchao.core.network.cookie.cache.SetCookieCache
import com.dongchao.core.network.cookie.persistence.SharedPrefsCookiePersistor
import com.facebook.stetho.okhttp3.StethoInterceptor

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://www.wanandroid.com"

//连接时长，单位：秒
private const val CONNECT_TIME_OUT = 10

//同一host并发数
private const val MAX_REQUESTS_PER_HOST = 64

@Module
@InstallIn(ActivityComponent::class)
class NetWorkModule {

    @Provides
    fun providerPersistentCookieJar(): PersistentCookieJar? {
        return try {
            var app = Utils.getApp()
            var cookieJar = PersistentCookieJar(
                SetCookieCache(),
                SharedPrefsCookiePersistor(app)
            )
            cookieJar
        } catch (e: Exception) {
            null
        }
    }

    @Provides
    fun providerOkHttpClient(persistentCookieJar: PersistentCookieJar): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            ).addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .cookieJar(persistentCookieJar)
            .build()
        okHttpClient.dispatcher.maxRequestsPerHost = MAX_REQUESTS_PER_HOST
        return okHttpClient
    }

    @Provides
    fun providerRetrofitNetApi(okHttpClient: OkHttpClient): RetrofitProcessor.RetrofitApi<*> {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitProcessor.RetrofitApi::class.java)
    }
}