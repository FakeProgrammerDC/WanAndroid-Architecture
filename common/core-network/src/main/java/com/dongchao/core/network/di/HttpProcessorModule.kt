package com.dongchao.core.network.di

import com.dongchao.core.network.annotation.BindOkHttp
import com.dongchao.core.network.annotation.BindRetrofit
import com.dongchao.core.network.IHttpProcessor
import com.dongchao.core.network.OkHttpProcessor
import com.dongchao.core.network.RetrofitProcessor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HttpProcessorModule {

    @Singleton
    @BindOkHttp
    @Binds
    abstract fun bindOkHttp(okHttpProcessor: OkHttpProcessor): IHttpProcessor

    @Singleton
    @BindRetrofit
    @Binds
    abstract fun bindRetrofit(retrofitProcessor: RetrofitProcessor): IHttpProcessor

}



