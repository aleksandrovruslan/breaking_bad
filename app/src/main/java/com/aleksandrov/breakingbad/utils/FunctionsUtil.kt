package com.aleksandrov.breakingbad.utils

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import com.aleksandrov.breakingbad.R
import com.google.android.material.snackbar.Snackbar

fun View.showMessage(message: String, @ColorRes messageColor: Int = R.color.dark_green) {
    val snackBar: Snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    (snackBar.view.layoutParams as FrameLayout.LayoutParams).gravity = Gravity.TOP
    snackBar.view.setBackgroundColor(this.context.getColor(messageColor))
    snackBar.show()
}

fun View.showError(errorMessage: String) {
    showMessage(errorMessage, R.color.red)
}