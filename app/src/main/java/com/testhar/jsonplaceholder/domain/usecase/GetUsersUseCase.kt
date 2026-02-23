package com.testhar.jsonplaceholder.domain.usecase

import com.testhar.jsonplaceholder.domain.model.User
import com.testhar.jsonplaceholder.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): List<User> = repository.getUsers()
}