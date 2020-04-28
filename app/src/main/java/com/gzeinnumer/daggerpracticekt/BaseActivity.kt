package com.gzeinnumer.daggerpracticekt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.gzeinnumer.daggerpracticekt.ui.auth.AuthActivity
import com.gzeinnumer.daggerpracticekt.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: created")
        Toast.makeText(this, "BaseActivity", Toast.LENGTH_SHORT).show()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.authUser
            .observe(this, Observer { responseLoginAuthResource ->
                if (responseLoginAuthResource != null) {
                    when (responseLoginAuthResource.status) {
                        AuthResource.AuthStatus.LOADING -> {
                        }
                        AuthResource.AuthStatus.AUTHENTICATED -> Log.d(
                            TAG,
                            "onChanged: Login AUTHENTICATED " + responseLoginAuthResource.data?.email
                        )
                        AuthResource.AuthStatus.ERROR -> Log.d(
                            TAG,
                            "onChanged: Login ERROR " + responseLoginAuthResource.message.toString() + " Only 1-10 Number avaliable"
                        )
                        AuthResource.AuthStatus.NOT_AUTHENTICATED -> navLoginScreen()
                    }
                }
            })
    }

    private fun navLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val TAG = "BaseActivity"
    }
}
