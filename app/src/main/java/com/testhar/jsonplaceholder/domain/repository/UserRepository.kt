package com.testhar.jsonplaceholder.domain.repository

import com.testhar.jsonplaceholder.domain.common.AppResult
import com.testhar.jsonplaceholder.domain.model.User

interface UserRepository {
    suspend fun getUsers() : AppResult<List<User>>
}