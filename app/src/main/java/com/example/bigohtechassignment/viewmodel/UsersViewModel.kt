package com.example.bigohtechassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.bigohtechassignment.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    repository: UsersRepository
) : ViewModel() {

    val getUsers = repository.getUsers().cachedIn(viewModelScope)

}