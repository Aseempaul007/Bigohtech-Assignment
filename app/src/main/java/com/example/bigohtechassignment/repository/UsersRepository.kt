package com.example.bigohtechassignment.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.bigohtechassignment.api.UsersApi
import com.example.bigohtechassignment.data.UserItem
import com.example.bigohtechassignment.paging.UserPagingSource
import javax.inject.Inject

class UsersRepository @Inject constructor(private val api: UsersApi) {

    // function to get list of all users
    fun getUsers() = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { UserPagingSource(api) }
    ).liveData
}