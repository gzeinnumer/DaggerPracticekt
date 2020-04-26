package com.gzeinnumer.daggerpracticekt.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AuthVM @Inject constructor(): ViewModel(){
    private val TAG = "AuthVM"

    init {
        Log.d(TAG, "AuthVM: viewmodel sudah bekerja")
    }
}