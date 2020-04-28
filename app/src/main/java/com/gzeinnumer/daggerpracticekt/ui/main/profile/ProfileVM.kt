package com.gzeinnumer.daggerpracticekt.ui.main.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ProfileVM @Inject constructor() : ViewModel() {
    companion object {
        private const val TAG = "ProfileVM"
    }

    init {
        Log.d(TAG, "ProfileVM: ready...")
    }
}