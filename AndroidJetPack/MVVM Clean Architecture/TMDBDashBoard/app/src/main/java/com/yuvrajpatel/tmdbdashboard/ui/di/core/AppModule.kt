package com.yuvrajpatel.tmdbdashboard.ui.di.core

import android.content.Context
import com.yuvrajpatel.tmdbdashboard.ui.di.actor.ActorSubComponent
import com.yuvrajpatel.tmdbdashboard.ui.di.movie.MovieSubComponent
import com.yuvrajpatel.tmdbdashboard.ui.di.tvshow.TvShowSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [
MovieSubComponent::class,
ActorSubComponent::class,
TvShowSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}