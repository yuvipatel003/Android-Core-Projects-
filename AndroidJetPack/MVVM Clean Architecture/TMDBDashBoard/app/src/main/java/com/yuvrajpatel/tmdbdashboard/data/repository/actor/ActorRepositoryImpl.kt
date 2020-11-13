package com.yuvrajpatel.tmdbdashboard.data.repository.actor

import android.util.Log
import com.yuvrajpatel.tmdbdashboard.data.model.actor.Actor
import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource.ActorCacheDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource.ActorLocalDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource.ActorRemoteDataSource
import com.yuvrajpatel.tmdbdashboard.domain.repository.ActorRepository
import java.lang.Exception

class ActorRepositoryImpl(
    private val actorRemoteDataSource: ActorRemoteDataSource,
    private val actorLocalDataSource: ActorLocalDataSource,
    private val actorCacheDataSource: ActorCacheDataSource
) : ActorRepository {

    private val mTag = ActorRepositoryImpl::class.java.simpleName

    override suspend fun getActors(): List<Actor>? {
        return getActorsFromCache()
    }

    override suspend fun updateActors(): List<Actor>? {
        val newActorList = getActorsFromAPI()
        actorLocalDataSource.clearAll()
        actorLocalDataSource.saveActorToDB(newActorList)
        actorCacheDataSource.saveActorToCache(newActorList)
        return newActorList
    }

    private suspend fun getActorsFromAPI() : List<Actor>{
        lateinit var actorList : List<Actor>
        try {
            val response = actorRemoteDataSource.getActors()
            val body = response.body()
            if(body!=null){
                actorList = body.actors
            }
        }catch (e:Exception){
            Log.d(mTag,"Exception : $e")
        }
        return actorList
    }

    private suspend fun getActorsFromDB() : List<Actor>{
        lateinit var actorList : List<Actor>
        try {
            actorList = actorLocalDataSource.getActorFromDB()
        }catch (e:Exception){
            Log.d(mTag,"Exception : $e")
        }

        if(actorList.isNotEmpty()){
            return actorList
        } else {
            actorList = getActorsFromAPI()
            actorLocalDataSource.saveActorToDB(actorList)
        }
        return actorList
    }

    private suspend fun getActorsFromCache() : List<Actor> {
        lateinit var actorList : List<Actor>
        try {
            actorList = actorCacheDataSource.getActorsFromCache()
        }catch (e:Exception){
            Log.d(mTag,"Exception : $e")
        }

        if(actorList.isNotEmpty()){
            return actorList
        } else {
            actorList = getActorsFromDB()
            actorCacheDataSource.saveActorToCache(actorList)
        }
        return actorList
    }
}