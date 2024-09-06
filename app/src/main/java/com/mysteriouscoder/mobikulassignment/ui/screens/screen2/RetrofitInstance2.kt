package com.mysteriouscoder.mobikulassignment.ui.screens.screen2

import com.mysteriouscoder.mobikulassignment.ui.screens.ApiService
import com.mysteriouscoder.mobikulassignment.data.User
import com.mysteriouscoder.mobikulassignment.data.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitInstance2 {
    private val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    suspend fun fetchUser(userId: Int): User {
        return apiService.fetchUser(userId)
    }

    suspend fun fetchUserPosts(userId: Int): List<Post> {
        return apiService.fetchUserPosts(userId)
    }
}
