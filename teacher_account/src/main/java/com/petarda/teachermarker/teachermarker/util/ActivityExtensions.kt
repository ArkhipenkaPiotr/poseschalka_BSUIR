package com.petarda.teachermarker.teachermarker.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Activity.hideKeyboard(view: View?) {
    val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view?.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
}

fun Activity.hideKeyboard() {
    val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = this.currentFocus
    inputMethodManager.hideSoftInputFromWindow(view?.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
}

fun Activity.showKeyboard() {
    val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun Activity.showToast(text: String, lenght: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, lenght).show()
}