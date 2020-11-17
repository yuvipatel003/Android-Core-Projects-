package com.yuvrajpatel.tmdbdashboard.ui.di.actor

import com.yuvrajpatel.tmdbdashboard.domain.usecase.actor.GetActorUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.actor.UpdateActorUseCase
import com.yuvrajpatel.tmdbdashboard.ui.actor.ActorViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ActorModule {

    @ActorScope
    @Provides
    fun provideActorActivityViewModelFactory(
        getActorUseCase: GetActorUseCase,
        updateActorUseCase: UpdateActorUseCase
    ) : ActorViewModelFactory{
        return ActorViewModelFactory(getActorUseCase, updateActorUseCase)
    }

}