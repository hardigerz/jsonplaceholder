package com.testhar.jsonplaceholder.data.repository

import com.testhar.jsonplaceholder.data.mapper.toDomain
import com.testhar.jsonplaceholder.data.remote.ApiService
import com.testhar.jsonplaceholder.domain.model.User
import com.testhar.jsonplaceholder.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api : ApiService
) : UserRepository{
    override suspend fun getUsers(): List<User> {
        return api.getUsers().map { it.toDomain() }
    }
}