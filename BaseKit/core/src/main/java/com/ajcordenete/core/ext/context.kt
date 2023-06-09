package com.ajcordenete.core.ext

import android.content.Context
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import com.google.android.material.color.MaterialColors

@ColorInt
fun Context.getThemeColor(@AttrRes attrResId: Int): Int {
    return MaterialColors
        .getColor(
            this,
            attrResId,
            android.graphics.Color.TRANSPARENT
        )
}