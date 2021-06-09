package com.juarai.capstone.ui.validation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juarai.capstone.data.Repository
import com.juarai.capstone.data.local.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ValidationViewModel(private val repository: Repository): ViewModel() {

    fun insert(userEntity: UserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(userEntity)
        }
    }


//    fun getDataKK(no_kk : Int) = liveData(Dispatchers.IO) {
//        val module: Module = get()
//    }
}