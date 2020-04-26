package com.gzeinnumer.daggerpracticekt.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.gzeinnumer.daggerpracticekt.R
import com.gzeinnumer.daggerpracticekt.vm.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    private val TAG = "AuthActivity"

    @Inject
    lateinit var str: String

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: AuthVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        Log.d(TAG, "onCreate: $str")

        setLogo()

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthVM::class.java)
    }

    private fun setLogo() {
        requestManager.load(logo)
            .into((findViewById<View>(R.id.login_logo) as ImageView))
    }
}
