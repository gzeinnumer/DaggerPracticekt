package com.gzeinnumer.daggerpracticekt.di.main

import com.gzeinnumer.daggerpracticekt.network.mainApi.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainModule {
    @Provides
    fun providesMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }
}