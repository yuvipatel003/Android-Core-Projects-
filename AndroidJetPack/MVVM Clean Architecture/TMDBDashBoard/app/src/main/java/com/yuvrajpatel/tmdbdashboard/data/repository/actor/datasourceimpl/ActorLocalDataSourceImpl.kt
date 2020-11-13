package com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasourceimpl

import com.yuvrajpatel.tmdbdashboard.data.dao.ActorDao
import com.yuvrajpatel.tmdbdashboard.data.model.actor.Actor
import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource.ActorLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActorLocalDataSourceImpl(
    private val actorDao: ActorDao
) : ActorLocalDataSource {
    override suspend fun getActorFromDB(): List<Actor> {
       return actorDao.getActors()
    }

    override suspend fun saveActorToDB(actors: List<Actor>) {
       CoroutineScope(Dispatchers.IO).launch {
           actorDao.saveActors(actors)
       }
    }

    override suspend fun clearAll() {
       CoroutineScope(Dispatchers.IO).launch {
           actorDao.deleteAllActors()
       }
    }
}