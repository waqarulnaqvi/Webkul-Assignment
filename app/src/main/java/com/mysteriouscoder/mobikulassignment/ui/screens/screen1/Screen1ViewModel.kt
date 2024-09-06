package com.mysteriouscoder.mobikulassignment.ui.screens.screen1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mysteriouscoder.mobikulassignment.data.User
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Screen1ViewModel : ViewModel() {

    // MutableStateFlow for managing search text state
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    // MutableStateFlow for tracking search progress
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    // MutableStateFlow for the list of users
    private val _userList = MutableStateFlow<List<User>>(emptyList())
//    val userList = _userList.asStateFlow()

    // Debounced search with flow combination for search filtering
    @OptIn(FlowPreview::class)
    val userList = searchText
        .debounce(500L)
        .onEach { _isSearching.update { true } }
        .combine(_userList) { text, userList ->
            if (text.isBlank()) {
                userList
            } else {
                delay(1000L)
                // Simulate network delay for filtering
                val filteredList = userList.filter {
                    val idString = it.id.toString()
                    it.name.contains(text.trim(), ignoreCase = true) || idString.contains(text.trim())
                }
                filteredList
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _userList.value // Initial value
        )

    // Initialize by fetching users
    init {
        fetchUsers()
    }

    // Fetch the users using Retrofit and update the list
    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val users = RetrofitInstance1.api.getUsers()
                _userList.value = users
            } catch (e: Exception) {
                // Log or show error message
                _userList.value = emptyList()

            }
        }
    }

    // Update the search text state
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}

