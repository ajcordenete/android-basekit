package com.ajcordenete.data.core

import com.ajcordenete.domain.models.User
import com.ajcordenete.persistence.features.user.models.UserDB
import com.ajcordenete.network.feature.user.models.UserDTO

fun User.asEntity(): UserDB {
    this.apply {
        return UserDB(
            uid = uid,
            fullName = fullName,
            email = email
        )
    }
}

fun UserDB.asDomain(): User {
    this.apply {
        return User(
            uid = uid,
            fullName = fullName.orEmpty(),
            email = email.orEmpty()
        )
    }
}

fun User.asDTO(): UserDTO {
    this.apply {
        return UserDTO(
            uid = uid,
            name = fullName,
            email = email
        )
    }
}

fun UserDTO.asDomain(): User {
    this.apply {
        return User(
            uid = uid,
            fullName = name,
            email = email
        )
    }
}