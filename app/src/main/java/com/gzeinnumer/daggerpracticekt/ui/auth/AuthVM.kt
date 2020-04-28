package com.gzeinnumer.daggerpracticekt.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.gzeinnumer.daggerpracticekt.SessionManager
import com.gzeinnumer.daggerpracticekt.network.authApi.AuthApi
import com.gzeinnumer.daggerpracticekt.network.authApi.model.ResponseLogin
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//ingat object yang ada didalam function yang di inject, itu sudah ada di @Provide Module
@Suppress("CAST_NEVER_SUCCEEDS", "SENSELESS_COMPARISON")
class AuthVM @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
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
    }

    private val authUser = MediatorLiveData<AuthResource<ResponseLogin>>()
    fun authWithId(userId: Int) {
        Log.d(TAG, "authWithId: attempting to login")
        sessionManager.authenticatedWithId(queryUserId(userId))
    }

    fun observeAuthState(): LiveData<AuthResource<ResponseLogin>> {
        return sessionManager.authUser;
    }

    private fun queryUserId(userId: Int): LiveData<AuthResource<ResponseLogin>> {
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                .onErrorReturn {
                    val responseLogin = ResponseLogin()
                    responseLogin.id = -1
                    responseLogin
                }
                .map(object : Function<ResponseLogin, AuthResource<ResponseLogin>>{
                    override fun apply(responseLogin: ResponseLogin): AuthResource<ResponseLogin> {
                        if(responseLogin.id == -1){
                            return AuthResource.error("Could not aurhenticate")
                        }
                        return AuthResource.authenticated(responseLogin)
                    }
                })
                .subscribeOn(Schedulers.io())
        )
    }
}