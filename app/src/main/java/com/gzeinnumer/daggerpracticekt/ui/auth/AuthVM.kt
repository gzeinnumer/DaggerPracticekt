package com.gzeinnumer.daggerpracticekt.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gzeinnumer.daggerpracticekt.network.authApi.AuthApi
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
    }
}