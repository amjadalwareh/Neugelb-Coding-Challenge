package com.neugelb.challenge

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class NeugelbTestApplication : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}