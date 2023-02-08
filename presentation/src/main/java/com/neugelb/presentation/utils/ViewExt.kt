package com.neugelb.presentation.utils

import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

fun ImageView.load(url: String?) {
    if (url.isNullOrEmpty()) return

    Glide.with(this)
        .load("https://image.tmdb.org/t/p/w500/$url")
        .into(this)
}

fun EditText?.onClickSearch(block: (String) -> Unit) {
    this?.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            block(this.text.toString())
            this.hideKeyboard()
            return@OnEditorActionListener true
        }
        false
    })
}

fun EditText?.hideKeyboard() {
    val imm = this?.context?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}