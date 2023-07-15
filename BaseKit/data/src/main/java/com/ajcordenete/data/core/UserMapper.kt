package com.ajcordenete.data.core

import com.ajcordenete.domain.models.User
import com.ajcordenete.persistence.features.user.models.UserDB
import com.ajcordenete.network.feature.user.models.UserDTO

fun User.asEntity(): UserDB {
    this.apply {
        return UserDB(
            id = id,
            firstName = firstName,
            lastName = lastName,
            fullName = fullName,
            email = email
        )
    }
}

fun UserDB.asDomain(): User {
    this.apply {
        return User(
            id = id,
            firstName = firstName.orEmpty(),
            lastName = lastName.orEmpty(),
            fullName = fullName.orEmpty(),
            email = email.orEmpty()
        )
    }
}

fun User.asDTO(): UserDTO {
    this.apply {
        return UserDTO(
            id = id,
            firstName = firstName,
            lastName = lastName,
            fullName = fullName,
            email = email
        )
    }
}

fun UserDTO.asDomain(): User {
    this.apply {
        return User(
            id = id ?: 0L,
            firstName = firstName.orEmpty(),
            lastName = lastName.orEmpty(),
            fullName = fullName.orEmpty(),
            email = email.orEmpty()
        )
    }
}