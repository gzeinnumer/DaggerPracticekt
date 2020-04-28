package com.gzeinnumer.daggerpracticekt

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.gzeinnumer.daggerpracticekt.network.authApi.model.ResponseLogin
import com.gzeinnumer.daggerpracticekt.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    private val chacedUser: MediatorLiveData<AuthResource<ResponseLogin>> =
        MediatorLiveData<AuthResource<ResponseLogin>>()

    fun authenticatedWithId(source: LiveData<AuthResource<ResponseLogin>>?) {
        if (chacedUser != null) {
            chacedUser.value = AuthResource.loading()
            chacedUser.addSource(
                source!!
            ) { responseLoginAuthResource ->
                chacedUser.value = responseLoginAuthResource
                chacedUser.removeSource(source)
            }
        }
    }

    fun logOut() {
        Log.d(TAG, "logOut: logging out")
        chacedUser.value = AuthResource.logout()
    }

    val authUser: LiveData<AuthResource<ResponseLogin>>
        get() = chacedUser

    companion object {
        private const val TAG = "SessionManager"
    }
}