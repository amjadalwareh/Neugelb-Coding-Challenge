package com.neugelb.challenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neugelb.challenge.databinding.ActivityMainBinding
import com.neugelb.presentation.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    private val viewBinding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}