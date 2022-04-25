package com.challenge.mozper.util

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showErrorToast(error: String) {
    view?.let { Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show() }
}

fun Activity.showErrorToast(error: String) {
    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
}