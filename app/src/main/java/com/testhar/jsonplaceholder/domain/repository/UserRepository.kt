package com.testhar.jsonplaceholder.domain.repository

import com.testhar.jsonplaceholder.domain.model.User

interface UserRepository {
    suspend fun getUsers() : List<User>
}