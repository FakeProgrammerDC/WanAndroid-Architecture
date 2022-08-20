package com.dongchao.core.network.di

import com.dongchao.core.network.IHttpProcessor
import com.dongchao.core.network.RetrofitProcessor
import com.dongchao.core.network.annotation.BindRetrofit
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class HttpProcessorModule {

    //@BindRetrofit
    @Binds
    abstract fun bindRetrofit(retrofitProcessor: RetrofitProcessor<*>): IHttpProcessor

}



