package com.yuvrajpatel.tmdbdashboard.domain.usecase.actor

import com.yuvrajpatel.tmdbdashboard.data.model.actor.Actor
import com.yuvrajpatel.tmdbdashboard.domain.repository.ActorRepository

class UpdateActorUseCase(private val actorRepository: ActorRepository) {

    suspend fun execute(): List<Actor>? = actorRepository.updateActors()

}