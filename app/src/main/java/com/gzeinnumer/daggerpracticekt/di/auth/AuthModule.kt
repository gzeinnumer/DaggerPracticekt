package com.gzeinnumer.daggerpracticekt.di.auth

import com.gzeinnumer.daggerpracticekt.network.authApi.AuthApi
import com.gzeinnumer.daggerpracticekt.network.authApi.model.ResponseLogin
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
object AuthModule {
    @AuthScope
    @Provides
    fun providesAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    //scope-example
    //disini memori akan di create ulang
    //@Named untuk membuat 2 buah @Provides yang me return hal yang sama, kita butuh menggunakan @Named
    //dan gunakan @Named dinawah @Inject, lihat di MainActivity
//    @AuthScope
//    @Provides
//    @Named("auth_login")
//    fun responseLogin2(): ResponseLogin {
//        return ResponseLogin()
//    }
}