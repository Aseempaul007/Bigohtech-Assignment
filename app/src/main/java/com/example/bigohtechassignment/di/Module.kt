package com.example.bigohtechassignment.di

import com.example.bigohtechassignment.api.UsersApi
import com.example.bigohtechassignment.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    // to get retrofit instance
    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.USER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // to get users api instance
    @Provides
    @Singleton
    fun getUsersApi(retrofit: Retrofit): UsersApi {
        return retrofit.create(UsersApi::class.java)
    }

}