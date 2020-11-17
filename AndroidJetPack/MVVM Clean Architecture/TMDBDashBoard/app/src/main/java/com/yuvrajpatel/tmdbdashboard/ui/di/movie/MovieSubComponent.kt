package com.yuvrajpatel.tmdbdashboard.ui.di.movie

import com.yuvrajpatel.tmdbdashboard.ui.movie.MovieActivity

import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {

    fun inject(movieActivity: MovieActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create() : MovieSubComponent
    }
}