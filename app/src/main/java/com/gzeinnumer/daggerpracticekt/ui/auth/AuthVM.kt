package com.gzeinnumer.daggerpracticekt.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.gzeinnumer.daggerpracticekt.network.authApi.AuthApi
import com.gzeinnumer.daggerpracticekt.network.authApi.model.ResponseLogin
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//ingat object yang ada didalam function yang di inject, itu sudah ada di @Provide Module
class AuthVM @Inject constructor(
    private val authApi: AuthApi
) : ViewModel() {

    companion object {
        private const val TAG = "AuthVM"
    }

    init {
        Log.d(TAG, "AuthVM: viewmodel sudah bekerja")
        if (authApi == null) {
            Log.d(TAG, "AuthVM: api is NULL")
        } else {
            Log.d(TAG, "AuthVM: api is not NULL")
        }

        authApi.getUser(1)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<ResponseLogin> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(responseLogin: ResponseLogin) {
                    Log.d(TAG, "onNext: " + responseLogin.email)
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError: " + e.message)
                }

                override fun onComplete() {}
            })
    }

    private val authUser = MediatorLiveData<ResponseLogin>()
    fun authWithId(userId: Int) {
        val source = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                .subscribeOn(Schedulers.io())
        )
        authUser.addSource(
            source
        ) { responseLogin ->
            authUser.value = responseLogin
            authUser.removeSource(source)
        }
    }

    fun observeUser(): LiveData<ResponseLogin>? {
        return authUser
    }
}