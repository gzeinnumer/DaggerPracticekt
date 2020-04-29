package com.gzeinnumer.daggerpracticekt.di.main

import com.gzeinnumer.daggerpracticekt.network.mainApi.MainApi
import com.gzeinnumer.daggerpracticekt.ui.main.post.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainModule {
    @MainScope
    @Provides
    fun providesMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @MainScope
    @Provides
    fun providesRecyclerAdapter(): PostsRecyclerAdapter {
        return PostsRecyclerAdapter()
    }
}