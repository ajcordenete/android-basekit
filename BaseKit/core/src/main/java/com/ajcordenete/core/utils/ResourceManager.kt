package com.ajcordenete.core.utils

import dagger.hilt.android.qualifiers.ApplicationContext
import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.util.Patterns
import androidx.annotation.*
import java.util.*
import javax.inject.Inject

/**
 * Holder for android related calls so view models would still be testable.
 */
class ResourceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    @ColorInt
    fun getColor(@ColorRes resId: Int): Int {
        return context.getColor(resId)
    }

    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }

    fun getDimen(@DimenRes resId: Int) = context.resources.getDimension(resId)

    fun getInteger(@IntegerRes resId: Int) = context.resources.getInteger(resId)

    fun getAndroidPackageName() = context.applicationContext.packageName

    @SuppressLint("HardwareIds")
    fun getDeviceId(): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun getStringArray(@ArrayRes resId: Int): Array<String> {
        return context.resources.getStringArray(resId)
    }

    fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun getQuantityString(@PluralsRes resId: Int, quantity: Int, vararg formatArgs: Any): String {
        return context.resources.getQuantityString(resId, quantity, *formatArgs)
    }
}