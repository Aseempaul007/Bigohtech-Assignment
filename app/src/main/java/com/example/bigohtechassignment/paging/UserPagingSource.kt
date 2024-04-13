package com.example.bigohtechassignment.paging

import androidx.paging.PagingSource
import com.example.bigohtechassignment.api.UsersApi
import com.example.bigohtechassignment.data.UserItem
import java.lang.Exception

// paging adapter for user loading in chunks
class UserPagingSource(private val api: UsersApi) : PagingSource<Int, UserItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserItem> {
        return try {
            val position = params.key ?: 1
            val response = api.getPhotos(position, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.size) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}