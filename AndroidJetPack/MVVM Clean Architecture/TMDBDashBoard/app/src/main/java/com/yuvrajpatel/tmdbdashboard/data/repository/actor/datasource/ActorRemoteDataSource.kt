package com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource

import com.yuvrajpatel.tmdbdashboard.data.model.actor.ActorList
import retrofit2.Response

interface ActorRemoteDataSource {
    suspend fun getActors() : Response<ActorList>
}