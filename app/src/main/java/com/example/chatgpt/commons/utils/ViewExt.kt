package com.example.chatgpt.commons.utils

import android.app.Service
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}

const val DELAY = 200L
fun delayAction(delay: Long = DELAY, action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        action.invoke()
    }, delay)
}