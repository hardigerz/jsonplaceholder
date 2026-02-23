package com.testhar.jsonplaceholder.data.mapper

import com.testhar.jsonplaceholder.data.remote.UserDTO
import com.testhar.jsonplaceholder.domain.model.User

fun UserDTO.toDomain(): User = User(
    id = id,
    name = name,
    email = email
)