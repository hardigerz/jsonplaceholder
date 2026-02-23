package com.testhar.jsonplaceholder.ui.presentation.users

import com.testhar.jsonplaceholder.domain.model.User

//data class UserState(
//    val isLoading: Boolean = false,
//    val data: List<User> = emptyList(),
//    val errorMessage: String? = null
//)
sealed interface UserState {
    data object Loading : UserState
    data class Success(val data: List<User>, val isRefreshing: Boolean = false) : UserState
    data class Error(val message: String) : UserState
}