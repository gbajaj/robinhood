package com.gauravbajaj.interviewready.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gauravbajaj.interviewready.data.model.DataModel
import com.gauravbajaj.interviewready.data.repository.Repository
import com.gauravbajaj.interviewready.ui.base.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<List<DataModel>>>(UIState.Initial)
    val uiState: StateFlow<UIState<List<DataModel>>> = _uiState

    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            try {
                userRepository.getUsers()
                    .collect { users ->
                        _uiState.value = UIState.Success(users)
                    }
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e.message ?: "Failed to load users")
            }
        }
    }
}
