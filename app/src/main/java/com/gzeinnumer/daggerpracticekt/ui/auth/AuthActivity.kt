package com.gzeinnumer.daggerpracticekt.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.gzeinnumer.daggerpracticekt.R
import com.gzeinnumer.daggerpracticekt.vm.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
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

        login_button.setOnClickListener(View.OnClickListener {
            if (TextUtils.isEmpty(user_id_input.text.toString())) {
                return@OnClickListener
            }
            viewModel.authWithId(user_id_input.text.toString().toInt())
        })
        subcribeObservers()
    }

    private fun setLogo() {
        requestManager.load(logo)
            .into((findViewById<View>(R.id.login_logo) as ImageView))
    }

    private fun subcribeObservers() {
        viewModel.observeUser()?.observe(this,
            Observer { responseLogin ->
                if (responseLogin != null) {
                    Log.d(TAG, "onChanged: " + responseLogin.email)
                }
            })
    }
}
