package com.mysteriouscoder.mobikulassignment.ui.screens.screen2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Screen2ViewModelFactory(private val userId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Screen2ViewModel(userId) as T
    }
}


