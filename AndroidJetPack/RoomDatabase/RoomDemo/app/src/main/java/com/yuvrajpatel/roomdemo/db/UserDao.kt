package com.yuvrajpatel.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun inserUser(user: User) : Long

    @Delete
    suspend fun deleteUser(user: User) : Int

    @Update
    suspend fun updateUser(user: User) : Int

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers() : Int

    @Query("SELECT * FROM user_table")
    fun getAllUses():LiveData<List<User>>

}