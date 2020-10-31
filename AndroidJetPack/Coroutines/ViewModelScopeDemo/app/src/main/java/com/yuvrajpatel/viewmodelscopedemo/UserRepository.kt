package com.yuvrajpatel.viewmodelscopedemo

import kotlinx.coroutines.delay

class UserRepository {

    suspend fun getUsers() : List<User>{
        delay(8000)
        val users : List<User> = listOf(
            User(1, "Alex"),
            User(2, "Bella"),
            User(3, "Cat"),
            User(4, "Don"),
            User(5, "Elle"),
            User(6, "Fire")
        )
        return users
    }
}