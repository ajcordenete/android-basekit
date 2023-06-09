package com.ajcordenete.core.utils

import com.ajcordenete.core.DATE_TIME_FORMAT_HOUR_MINUTE
import java.text.ParseException
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateTimeUtils {

    fun getDateTimeString(
        time: Long,
        outputFormat: String = DATE_TIME_FORMAT_HOUR_MINUTE
    ): String {
        time.let {
            return try {
                val outputFormatter = DateTimeFormatter.ofPattern(
                    outputFormat,
                    Locale.ENGLISH
                )
                val date = Instant
                    .ofEpochSecond(it / 1000L)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime()

                outputFormatter.format(date)
            } catch (e: ParseException) {
                it.toString()
            }
        }
        return ""
    }
}