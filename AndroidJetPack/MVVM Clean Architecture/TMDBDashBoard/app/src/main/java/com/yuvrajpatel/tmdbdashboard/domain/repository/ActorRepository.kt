package com.yuvrajpatel.tmdbdashboard.domain.repository

import com.yuvrajpatel.tmdbdashboard.data.model.actor.Actor

interface ActorRepository {

    suspend fun getActors(): List<Actor>?
    suspend fun updateActors(): List<Actor>?
}