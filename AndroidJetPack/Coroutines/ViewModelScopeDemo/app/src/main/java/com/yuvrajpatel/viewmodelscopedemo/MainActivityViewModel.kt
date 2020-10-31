package com.yuvrajpatel.viewmodelscopedemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*


class MainActivityViewModel : ViewModel() {

    private var userRepository = UserRepository()

    var users = liveData(Dispatchers.IO) {
        val result = userRepository.getUsers()
        emit(result)
    }

//    var users : MutableLiveData<List<User>> = MutableLiveData()
//
// fun getUserData(){
//
//     viewModelScope.launch {
//        var result : List<User>? = null
//
//         withContext(Dispatchers.IO){
//             result = userRepository.getUsers()
//         }
//         users.value = result
//     }
//
// }

}