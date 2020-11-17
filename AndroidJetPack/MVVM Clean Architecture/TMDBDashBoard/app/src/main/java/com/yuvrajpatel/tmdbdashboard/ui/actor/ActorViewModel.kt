package com.yuvrajpatel.tmdbdashboard.ui.actor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.yuvrajpatel.tmdbdashboard.domain.usecase.actor.GetActorUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.actor.UpdateActorUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.movie.GetMoviesUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.movie.UpdateMoviesUseCase

class ActorViewModel(
    private val getActorUseCase: GetActorUseCase,
    private val updateActorUseCase: UpdateActorUseCase
) : ViewModel() {

    fun getActors() = liveData {
        val movieList = getActorUseCase.execute()
        emit(movieList)
    }

    fun updateActors() = liveData {
        val movieList = updateActorUseCase.execute()
        emit(movieList)
    }
}