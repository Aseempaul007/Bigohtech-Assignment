package com.example.bigohtechassignment.api

import com.example.bigohtechassignment.data.User
import com.example.bigohtechassignment.data.UserItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    // get list of users
    @GET("v2/list")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<UserItem>

}