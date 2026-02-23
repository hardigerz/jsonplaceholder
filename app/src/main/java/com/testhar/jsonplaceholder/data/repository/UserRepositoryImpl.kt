package com.testhar.jsonplaceholder.data.repository

import com.testhar.jsonplaceholder.data.mapper.toDomain
import com.testhar.jsonplaceholder.data.remote.ApiService
import com.testhar.jsonplaceholder.domain.common.AppResult
import com.testhar.jsonplaceholder.domain.model.User
import com.testhar.jsonplaceholder.domain.repository.UserRepository
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api : ApiService
) : UserRepository{
    override suspend fun getUsers(): AppResult<List<User>> {
        return try {
            val result = api.getUsers().map { it.toDomain() }
            AppResult.Success(result)
        } catch (e: Exception) {
            val msg = when (e) {
                is IOException -> "Koneksi bermasalah. Coba lagi."
                else -> "Terjadi error: ${e.message ?: "Unknown"}"
            }
            AppResult.Error(message = msg, throwable = e)
        }
    }
}