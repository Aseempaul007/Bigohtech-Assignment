package com.example.bigohtechassignment.data

import java.io.Serializable

data class UserItem(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
) : Serializable