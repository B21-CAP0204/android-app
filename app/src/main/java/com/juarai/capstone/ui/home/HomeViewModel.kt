package com.juarai.capstone.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.juarai.capstone.data.Repository
import com.juarai.capstone.data.local.UserEntity

class HomeViewModel(private val repository: Repository): ViewModel() {
    fun getUser(): LiveData<List<UserEntity>> = repository.getUser()
}