package com.yuvrajpatel.tmdbdashboard.ui.actor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuvrajpatel.tmdbdashboard.domain.usecase.actor.GetActorUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.actor.UpdateActorUseCase

class ActorViewModelFactory(
    private val getActorUseCase: GetActorUseCase,
    private val updateActorUseCase: UpdateActorUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ActorViewModel(
            getActorUseCase,
            updateActorUseCase
        ) as T
    }
}