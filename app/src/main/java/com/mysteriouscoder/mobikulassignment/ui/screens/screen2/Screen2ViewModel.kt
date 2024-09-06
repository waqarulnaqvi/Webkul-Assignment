package com.mysteriouscoder.mobikulassignment.ui.screens.screen2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mysteriouscoder.mobikulassignment.data.User
import com.mysteriouscoder.mobikulassignment.data.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class Screen2ViewModel(private val userId: Int) : ViewModel() {
    private val _userInfo = MutableStateFlow<User?>(null)
    val userInfo: StateFlow<User?> = _userInfo

    private val _userPosts = MutableStateFlow<List<Post>>(emptyList())
    val userPosts: StateFlow<List<Post>> = _userPosts

    init {
        fetchUserInfo()
        fetchUserPosts()
    }

    private fun fetchUserInfo() {
        viewModelScope.launch {
            try {
                val user = RetrofitInstance2.fetchUser(userId)
                _userInfo.value = user
            } catch (e: Exception) {
                // Log or show error message
                _userInfo.value = null
            }
        }
    }

    private fun fetchUserPosts() {
        viewModelScope.launch {
            try {
                val posts = RetrofitInstance2.fetchUserPosts(userId)
                _userPosts.value = posts
            } catch (e: Exception) {
                // Log or show error message
                _userPosts.value = emptyList()
            }
        }
    }
}

