package com.neugelb.challenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neugelb.presentation.movie_details.MovieDetailsFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    private val viewBinding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MovieDetailsFragmentArgs
    }
}