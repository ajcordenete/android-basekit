package com.ajcordenete.common.ext

import android.app.Activity
import android.view.View
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("setVisible")
infix fun View.setVisible(isVisible: Boolean?) {
    if (isVisible == true && visibility == View.VISIBLE) {
        return
    }

    if (isVisible == false && visibility == View.GONE) {
        return
    }

    this.visibility = when (isVisible) {
        false -> View.GONE
        else -> View.VISIBLE
    }
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

@BindingAdapter("setHidden")
fun View.setHidden(isHidden: Boolean) {
    this.visibility = if (isHidden) {
        View.INVISIBLE
    } else {
        View.VISIBLE
    }
}

fun View.showKeyboard(activity: Activity) {
    WindowInsetsControllerCompat(activity.window, this).let { controller ->
        controller.show(WindowInsetsCompat.Type.ime())
    }
}

fun View.hideKeyboard(activity: Activity) {
    WindowInsetsControllerCompat(activity.window, this).let { controller ->
        controller.hide(WindowInsetsCompat.Type.ime())
    }
}

fun View.hideKeyboardClearFocus(activity: Activity) {
    hideKeyboard(activity)
    clearFocus()
}