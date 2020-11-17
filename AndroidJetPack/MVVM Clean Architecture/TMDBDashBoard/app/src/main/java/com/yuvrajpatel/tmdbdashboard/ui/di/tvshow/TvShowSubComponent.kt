package com.yuvrajpatel.tmdbdashboard.ui.di.tvshow

import com.yuvrajpatel.tmdbdashboard.ui.actor.ActorActivity
import com.yuvrajpatel.tmdbdashboard.ui.tvshow.TvShowActivity
import dagger.Subcomponent

@TvShowScope
@Subcomponent (modules = [TvShowModule::class])
interface TvShowSubComponent {

    fun inject(tvShowActivity: TvShowActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create() : TvShowSubComponent
    }
}