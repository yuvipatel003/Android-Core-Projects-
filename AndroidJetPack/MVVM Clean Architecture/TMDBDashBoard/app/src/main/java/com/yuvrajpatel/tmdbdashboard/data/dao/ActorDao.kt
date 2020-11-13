package com.yuvrajpatel.tmdbdashboard.data.dao

import androidx.room.*
import com.yuvrajpatel.tmdbdashboard.data.model.actor.Actor

@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveActors(movies : List<Actor>)

    @Query("SELECT * FROM popular_actors")
    suspend fun getActors() : List<Actor>

    @Query("DELETE FROM popular_actors")
    suspend fun deleteAllActors()
}