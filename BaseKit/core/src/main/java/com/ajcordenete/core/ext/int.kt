package com.ajcordenete.core.ext

import android.content.Context
import java.util.Random

fun IntRange.random() =
    Random().nextInt((endInclusive + 1) - start) + start

fun Int.toPx(context: Context): Int = (this * context.resources.displayMetrics.density).toInt()