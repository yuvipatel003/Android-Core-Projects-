package com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource

import com.yuvrajpatel.tmdbdashboard.data.model.actor.Actor

interface ActorLocalDataSource {
    suspend fun getActorFromDB() : List<Actor>
    suspend fun saveActorToDB(actors : List<Actor>)
    suspend fun clearAll()
}