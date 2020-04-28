package com.gzeinnumer.daggerpracticekt.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.gzeinnumer.daggerpracticekt.R
import com.gzeinnumer.daggerpracticekt.network.authApi.model.ResponseLogin
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

    private var progressBar: ProgressBar? = null
    private fun showLoading(isVisible: Boolean) {
        if (isVisible) {
            progressBar!!.visibility = View.VISIBLE
        } else {
            progressBar!!.visibility = View.GONE
        }
    }

    private fun subcribeObservers() {
        progressBar = findViewById(R.id.progress_bar)
        viewModel.observeUser()
            .observe(this,
                Observer {
                    when (it.status) {
                        AuthResource.AuthStatus.LOADING -> showLoading(true)
                        AuthResource.AuthStatus.AUTHENTICATED -> {
                            showLoading(false)
                            Log.d(
                                TAG,
                                "onChanged: Login AUTHENTICATED " + it.data?.email
                            )
                        }
                        AuthResource.AuthStatus.ERROR -> {
                            showLoading(false)
                            Log.d(
                                TAG,
                                "onChanged: Login ERROR " + it.message.toString() + " Only 1-10 Number avaliable"
                            )
                        }
                        AuthResource.AuthStatus.NOT_AUTHENTICATED -> showLoading(false)
                    }
                })
    }
}
