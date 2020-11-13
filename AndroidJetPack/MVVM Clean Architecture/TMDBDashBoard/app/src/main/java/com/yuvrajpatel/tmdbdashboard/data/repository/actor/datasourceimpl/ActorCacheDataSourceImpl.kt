package com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasourceimpl

import com.yuvrajpatel.tmdbdashboard.data.model.actor.Actor
import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource.ActorCacheDataSource

class ActorCacheDataSourceImpl :
    ActorCacheDataSource {
    private var actorList = ArrayList<Actor>()
    override suspend fun getActorsFromCache(): List<Actor> {
        return actorList
    }

    override suspend fun saveActorToCache(actors: List<Actor>) {
        actorList.clear()
        actorList = ArrayList(actors)
    }
}