package com.ajcordenete.core.ext

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * Launches [action] inside `activity lifecycleScope`.
 */
fun AppCompatActivity.launch(action: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        action(this)
    }
}