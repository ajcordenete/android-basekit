package com.ajcordenete.core.ext

import android.app.Activity
import android.view.View
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.BindingAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

const val CLICK_THROTTLE_TIME = 400L

@FlowPreview
@ExperimentalCoroutinesApi
fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> = flow {
    var lastEmissionTime = 0L
    collect { upstream ->
        val currentTime = System.currentTimeMillis()
        val mayEmit = currentTime - lastEmissionTime > windowDuration
        if (mayEmit)
        {
            lastEmissionTime = currentTime
            emit(upstream)
        }
    }
}

fun View.clicks(): Flow<Unit> = callbackFlow {
    setOnClickListener {
        trySend(Unit)
    }
    awaitClose { setOnClickListener(null) }
}

fun View.ninjaTap(onClick: (View) -> Unit): Flow<Unit> {
    return this.clicks().throttleFirst(CLICK_THROTTLE_TIME)
        .onEach {
            onClick(this)
        }
}

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