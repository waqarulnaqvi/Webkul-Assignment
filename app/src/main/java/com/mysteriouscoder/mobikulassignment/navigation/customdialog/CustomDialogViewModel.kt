package com.mysteriouscoder.mobikulassignment.navigation.customdialog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CustomDialogViewModel :ViewModel(){

    companion object {
        var isDialogShown by mutableStateOf(false)
            private set
        fun onDismissDialog() {
            isDialogShown = false
        }
        fun onClick(){
            isDialogShown = true
        }
    }


}