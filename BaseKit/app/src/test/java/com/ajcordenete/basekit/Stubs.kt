package com.ajcordenete.basekit

import com.ajcordenete.domain.models.User

object Stubs {

    val USERS = listOf(
        User("1", "Steph Curry", "steph@gmail.com"),
        User("2", "Draymond Green", "dray@gmail.com"),
        User("3", "Klay Thompson", "klay@gmail.com")
    )

    val ERROR_MESSAGE = "Request Failed"
    val ERROR = Exception(ERROR_MESSAGE)
}