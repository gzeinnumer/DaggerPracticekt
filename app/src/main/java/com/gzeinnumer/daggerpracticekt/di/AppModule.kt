package com.gzeinnumer.daggerpracticekt.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.gzeinnumer.daggerpracticekt.R
import com.gzeinnumer.daggerpracticekt.util.Constant
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule{

    @Singleton
    @Provides
    fun someString(): String {
        return "GZeinNumer"
    }

    @Singleton
    @Provides
    fun providesRequestOptions(): RequestOptions {
        return RequestOptions.placeholderOf(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
    }

    @Singleton
    @Provides
    fun providesRequestManager(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    fun providesDrawable(application: Application): Drawable {
        return ContextCompat.getDrawable(application, R.mipmap.ic_launcher)!!
    }

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}