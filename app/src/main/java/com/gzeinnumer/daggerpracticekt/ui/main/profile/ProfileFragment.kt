package com.gzeinnumer.daggerpracticekt.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gzeinnumer.daggerpracticekt.R
import com.gzeinnumer.daggerpracticekt.network.authApi.model.ResponseLogin
import com.gzeinnumer.daggerpracticekt.ui.auth.AuthResource
import com.gzeinnumer.daggerpracticekt.vm.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : DaggerFragment() {

    private val TAG = "ProfileFragment"

    lateinit var viewModel: ProfileVM

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: GZeinNumer");
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ProfileFragment was created")
        viewModel = ViewModelProviders.of(this, providerFactory).get(ProfileVM::class.java)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.getAuthenticationUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticationUser().observe(
            viewLifecycleOwner,
            Observer { responseLoginAuthResource ->
                if (responseLoginAuthResource != null) {
                    when (responseLoginAuthResource.status) {
                        AuthResource.AuthStatus.AUTHENTICATED -> responseLoginAuthResource.data?.let {
                            setUserDetails(it)
                        }
                        AuthResource.AuthStatus.ERROR -> responseLoginAuthResource.message?.let {
                            setErrorDetails(it)
                        }
                    }
                }
            })
    }


    private fun setUserDetails(data: ResponseLogin) {
        email.text = data.email
        username.text = data.username
        website.text = data.website
    }

    private fun setErrorDetails(message: String) {
        email.text = message
        username.text = "error"
        website.text = "error"
    }
}
