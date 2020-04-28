package com.gzeinnumer.daggerpracticekt.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.gzeinnumer.daggerpracticekt.R
import com.gzeinnumer.daggerpracticekt.vm.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
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
    }

}
