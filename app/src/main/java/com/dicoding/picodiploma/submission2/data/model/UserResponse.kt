package com.dicoding.picodiploma.submission2.data.model

data class UserResponse(
    val incomplete_results: Boolean,
    val items: ArrayList<Item>,
    val total_count: Int
)