package com.yuvrajpatel.tmdbdashboard.ui.di

import com.yuvrajpatel.tmdbdashboard.ui.di.actor.ActorSubComponent
import com.yuvrajpatel.tmdbdashboard.ui.di.movie.MovieSubComponent
import com.yuvrajpatel.tmdbdashboard.ui.di.tvshow.TvShowSubComponent

interface Injector {

    fun createMovieSubComponent(): MovieSubComponent
    fun createActorSubComponent(): ActorSubComponent
    fun createTvShowSubComponent(): TvShowSubComponent

}