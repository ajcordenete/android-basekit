package com.ajcordenete.core.ext

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.telephony.TelephonyManager
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.UnderlineSpan
import android.util.Base64
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.res.ResourcesCompat
import com.ajcordenete.core.BuildConfig
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.Charset
import java.time.Duration
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.regex.Pattern
import kotlin.math.abs

fun String.isEmailValid(): Boolean {
    return Pattern.compile(
        "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern
            .CASE_INSENSITIVE
    ).matcher(this).find()
}

@SuppressLint("DefaultLocale")
fun Context.getNetworkCountryIso(): String {
    // adb shell setprop gsm.sim.operator.iso-country ph
    if (BuildConfig.DEBUG) {
        return "ph".toUpperCase()
    }
    val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return tm.networkCountryIso.capitalize()
}

@Suppress("DEPRECATION")
@SuppressWarnings("deprecation")
fun String.toHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}

private fun String.convertImageBitmapToBase64(): String {
    val baos = ByteArrayOutputStream()
    val bitmap = BitmapFactory.decodeFile(File(this).absolutePath) ?: return ""
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    return "data:image/png;base64,".plus(Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT))
}

fun String.convertStringToBase64(): String {
    val data = this.toByteArray(Charset.forName("UTF-8"))
    return Base64.encodeToString(data, Base64.DEFAULT)
}

fun String.getTimeSpanString(): CharSequence {
    val odt = OffsetDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
    val difference = Duration.between(odt, ZonedDateTime.now()).toMillis() / 1000
    val hours = difference.div(3600)
    val minutes = difference.rem(3600).div(60)
    val seconds = difference.rem(60)
    val days = abs(hours / 24)
    val years = abs(days / 365)

    var dayTime = ""

    if (years > 1) {
        return DateTimeFormatter.ofPattern("dd MMMM yyyy").format(odt)
    }

    if (seconds < 60) {
        dayTime = if (seconds <= 0) {
            "now"
        } else {
            abs(seconds).toString() + "s"
        }
    }

    if (minutes in 1..60) {
        dayTime = abs(minutes).toString() + "m"
    }

    if (hours in 1..24) {
        dayTime = abs(hours).toString() + "h"
    }

    if (days in 1..7) {
        dayTime = abs(days).toString() + "d"
    }

    if (days in 8..31) {
        dayTime = DateTimeFormatter.ofPattern("d MMMM").format(odt)
    }

    return dayTime
}