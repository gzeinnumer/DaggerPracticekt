package com.gzeinnumer.daggerpracticekt.di.auth

import com.gzeinnumer.daggerpracticekt.network.authApi.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object AuthModule {
    @Provides
    fun providesAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}