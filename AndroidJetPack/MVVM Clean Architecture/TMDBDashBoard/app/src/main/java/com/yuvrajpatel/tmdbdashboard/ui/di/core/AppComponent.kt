package com.yuvrajpatel.tmdbdashboard.ui.di.core

import com.yuvrajpatel.tmdbdashboard.ui.di.actor.ActorSubComponent
import com.yuvrajpatel.tmdbdashboard.ui.di.movie.MovieSubComponent
import com.yuvrajpatel.tmdbdashboard.ui.di.tvshow.TvShowSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [
AppModule::class,
NetModule::class,
DatabaseModule::class,
UseCaseModule::class,
RepositoryModule::class,
RemoteDataModule::class,
LocalDataModule::class,
CacheDataModule::class
])
interface AppComponent {

    fun movieSubComponent(): MovieSubComponent.Factory
    fun actorSubComponent(): ActorSubComponent.Factory
    fun tvShowSubComponent(): TvShowSubComponent.Factory
}