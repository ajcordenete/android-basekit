package com.ajcordenete.core.utils

import androidx.core.net.toUri
import com.ajcordenete.core.R

object AppRoutes {

    object Main {
        object Deeplink {
            val LANDING = "basekit://com.ajcordenete.basekit/landing".toUri()
            val HOME = "basekit://com.ajcordenete.basekit/home".toUri()
        }
    }
}