package com.mysteriouscoder.mobikulassignment.ui.screens.screen3

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mysteriouscoder.mobikulassignment.ui.screens.ApiService
import com.mysteriouscoder.mobikulassignment.data.Comment
import com.mysteriouscoder.mobikulassignment.data.Post
import kotlinx.coroutines.launch

class Screen3ViewModel : ViewModel() {
    private val apiService: ApiService = RetrofitInstance3.apiService

    private val _postDetails = mutableStateOf<Post?>(null)
    val postDetails: State<Post?> get() = _postDetails

    private val _postComments = mutableStateOf<List<Comment>>(emptyList())
    val postComments: State<List<Comment>> get() = _postComments

    fun fetchPostDetails(postId: Int) {
        viewModelScope.launch {
            try {
                _postDetails.value = apiService.getPostDetails(postId)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun fetchPostComments(postId: Int) {
        viewModelScope.launch {
            try {
                _postComments.value = apiService.getPostComments(postId)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
