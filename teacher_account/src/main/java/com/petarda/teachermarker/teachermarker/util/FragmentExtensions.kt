package com.petarda.teachermarker.teachermarker.util

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast
import com.petarda.teachermarker.teachermarker.util.hideKeyboard
import com.petarda.teachermarker.teachermarker.util.showKeyboard

fun Fragment.showKeyboard() {
    activity?.showKeyboard()
}

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}

fun Fragment.showToast(text: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(context, text, duration).show()
}

fun Fragment.showToast(@StringRes res: Int, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(context, res, duration).show()
}