package com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource

import com.yuvrajpatel.tmdbdashboard.data.model.actor.Actor

interface ActorCacheDataSource {
    suspend fun getActorsFromCache() : List<Actor>
    suspend fun saveActorToCache(actors: List<Actor>)
}