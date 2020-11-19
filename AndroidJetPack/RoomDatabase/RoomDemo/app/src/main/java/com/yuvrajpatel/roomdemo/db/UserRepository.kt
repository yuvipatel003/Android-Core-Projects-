package com.yuvrajpatel.roomdemo.db

class UserRepository(private val dao : UserDao) {

    val users = dao.getAllUses()

    suspend fun insert(user: User) : Long {
        return dao.inserUser(user)
    }

    suspend fun update(user: User) : Int {
        return dao.updateUser(user)
    }

    suspend fun delete(user: User) : Int {
        return dao.deleteUser(user)
    }

    suspend fun deleteAll() : Int {
        return dao.deleteAllUsers()
    }

}