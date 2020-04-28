package com.gzeinnumer.daggerpracticekt.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gzeinnumer.daggerpracticekt.BaseActivity
import com.gzeinnumer.daggerpracticekt.R

class MainActivity : BaseActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: created")
        Toast.makeText(this, "MainActivity", Toast.LENGTH_SHORT).show()
    }
}
