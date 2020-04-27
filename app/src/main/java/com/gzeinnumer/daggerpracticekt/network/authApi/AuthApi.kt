package com.gzeinnumer.daggerpracticekt.network.authApi

import com.gzeinnumer.daggerpracticekt.network.authApi.model.ResponseLogin
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET("/users/{id}")
    fun getUser(@Path("id") id: Int): Flowable<ResponseLogin>
}