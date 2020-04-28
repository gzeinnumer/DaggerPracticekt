package com.gzeinnumer.daggerpracticekt.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gzeinnumer.daggerpracticekt.R

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    private val TAG = "ProfileFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: GZeinNumer");
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

}
