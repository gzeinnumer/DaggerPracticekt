package com.gzeinnumer.daggerpracticekt.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gzeinnumer.daggerpracticekt.SessionManager
import com.gzeinnumer.daggerpracticekt.network.authApi.model.ResponseLogin
import com.gzeinnumer.daggerpracticekt.ui.auth.AuthResource
import javax.inject.Inject

class ProfileVM @Inject constructor(private val sessionManager: SessionManager) : ViewModel() {
    companion object {
        private const val TAG = "ProfileVM"
    }

    init {
        Log.d(TAG, "ProfileVM: ready...")
    }

    fun getAuthenticationUser(): LiveData<AuthResource<ResponseLogin>> {
        return sessionManager.authUser
    }
}