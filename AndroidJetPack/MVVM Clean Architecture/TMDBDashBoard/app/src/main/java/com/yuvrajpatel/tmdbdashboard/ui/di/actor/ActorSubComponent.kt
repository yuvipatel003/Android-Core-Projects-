package com.yuvrajpatel.tmdbdashboard.ui.di.actor

import com.yuvrajpatel.tmdbdashboard.ui.actor.ActorActivity
import dagger.Subcomponent

@ActorScope
@Subcomponent (modules = [ActorModule::class])
interface ActorSubComponent {

    fun inject(actorActivity: ActorActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create() : ActorSubComponent
    }
}