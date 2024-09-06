package com.mysteriouscoder.mobikulassignment.ui.screens

import com.mysteriouscoder.mobikulassignment.data.Comment
import com.mysteriouscoder.mobikulassignment.data.Post
import com.mysteriouscoder.mobikulassignment.data.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    //Screen 1
    @GET("users")
    suspend fun getUsers(): List<User>

    //Screen 2
    @GET("users/{userId}")
    suspend fun fetchUser(@Path("userId") userId: Int): User

    @GET("users/{userId}/posts")
    suspend fun fetchUserPosts(@Path("userId") userId: Int): List<Post>

    //Screen 3
    @GET("posts/{postId}")
    suspend fun getPostDetails(@Path("postId") postId: Int): Post

    @GET("posts/{postId}/comments")
    suspend fun getPostComments(@Path("postId") postId: Int): List<Comment>
}

