package com.mysteriouscoder.mobikulassignment.ui.screens.screen3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mysteriouscoder.mobikulassignment.CommentsSection
import com.mysteriouscoder.mobikulassignment.Post
import com.mysteriouscoder.mobikulassignment.ui.theme.BabyBlue
import com.mysteriouscoder.mobikulassignment.ui.theme.LightGreen
import com.mysteriouscoder.mobikulassignment.ui.theme.RedOrange
import com.mysteriouscoder.mobikulassignment.ui.theme.RedPink
import com.mysteriouscoder.mobikulassignment.ui.theme.Violet

@Composable
fun Screen3(postId: Int, viewModel: Screen3ViewModel = viewModel()) {
    // Fetch data when postId changes
    LaunchedEffect(postId) {
        viewModel.fetchPostDetails(postId)
        viewModel.fetchPostComments(postId)
    }

    val postDetails by viewModel.postDetails
    val postComments by viewModel.postComments

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        postDetails?.let { post ->
            val color = when (post.id % 5) {
                1 -> LightGreen
                2 -> RedOrange
                3 -> RedPink
                4 -> BabyBlue
                else -> Violet
            }
            Spacer(modifier = Modifier.height(20.dp))
            Post(post = post,
                color = color
            )
            Spacer(modifier = Modifier.height(10.dp))
            CommentsSection(comments = postComments)
        }
    }
}
