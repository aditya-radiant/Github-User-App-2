package com.dicoding.picodiploma.submission2.data.remote
import com.dicoding.picodiploma.submission2.data.model.DetailUser
import com.dicoding.picodiploma.submission2.data.model.Item
import com.dicoding.picodiploma.submission2.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @GET("search/users")
    @Headers("Authorization: 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")
    fun getUserGithub(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users")
    @Headers("Authorization: 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")
    fun getUserGithubList(): Call<ArrayList<Item>>

    @GET("users/{username}")
    @Headers("Authorization: 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")
    fun getUserGithubDetail(
        @Path("username") username: String
    ): Call<DetailUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: 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")
    fun getUserGithubFollowers(
        @Path("username") username: String
    ): Call<ArrayList<Item>>

    @GET("users/{username}/following")
    @Headers("Authorization: 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")
    fun getUserGithubFollowing(
        @Path("username") username: String
    ): Call<ArrayList<Item>>
}