package com.testhar.jsonplaceholder.ui.presentation.users

import com.testhar.jsonplaceholder.domain.model.User

data class UserState(
    val isLoading: Boolean = false,
    val data: List<User> = emptyList(),
    val errorMessage: String? = null
)