package com.testhar.jsonplaceholder.ui.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testhar.jsonplaceholder.domain.common.AppResult
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

    private val _uiState = MutableStateFlow<UserState>(UserState.Loading)
    val uiState: StateFlow<UserState> = _uiState.asStateFlow()

    init {
        fetchUsers(initial = true)
    }

    fun retry() {
        fetchUsers(initial = true)
    }

    fun refresh() {
        fetchUsers(initial = false)
    }

    private fun fetchUsers(initial: Boolean) {
        viewModelScope.launch {
            val current = _uiState.value

            if (initial) {
                _uiState.value = UserState.Loading
            } else {
                // Refresh: kalau sudah success, tetap tampilkan data sambil refreshing
                if (current is UserState.Success) {
                    _uiState.value = current.copy(isRefreshing = true)
                } else {
                    _uiState.value = UserState.Loading
                }
            }

            when (val result = getUsers()) {
                is AppResult.Success -> {
                    _uiState.value = UserState.Success(
                        data = result.data,
                        isRefreshing = false
                    )
                }
                is AppResult.Error -> {
                    _uiState.value = UserState.Error(result.message)
                }
            }
        }
    }
}