package com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasourceimpl

import com.yuvrajpatel.tmdbdashboard.data.api.TMDBService
import com.yuvrajpatel.tmdbdashboard.data.model.actor.ActorList
import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource.ActorRemoteDataSource
import retrofit2.Response

class ActorRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey : String
) : ActorRemoteDataSource {
    override suspend fun getActors(): Response<ActorList> = tmdbService.getPopularActors(apiKey)
}