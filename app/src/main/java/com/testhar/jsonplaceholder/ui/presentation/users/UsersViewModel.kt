package com.testhar.jsonplaceholder.ui.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testhar.jsonplaceholder.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsers: GetUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserState(isLoading = true))
    val uiState: StateFlow<UserState> = _uiState.asStateFlow()

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _uiState.value = UserState(isLoading = true)

            try {
                val users = getUsers()
                _uiState.value = UserState(
                    isLoading = false,
                    data = users,
                    errorMessage = null
                )
            } catch (e: Exception) {
                val msg = when (e) {
                    is IOException -> "Koneksi bermasalah. Coba lagi."
                    else -> "Terjadi error: ${e.message ?: "Unknown"}"
                }
                _uiState.value = UserState(
                    isLoading = false,
                    data = emptyList(),
                    errorMessage = msg
                )
            }
        }
    }
}