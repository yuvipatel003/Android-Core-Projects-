package com.yuvrajpatel.tmdbdashboard.ui.di

import com.yuvrajpatel.tmdbdashboard.data.dao.ActorDao
import com.yuvrajpatel.tmdbdashboard.data.dao.MovieDao
import com.yuvrajpatel.tmdbdashboard.data.dao.TvShowDao
import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource.ActorLocalDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasourceimpl.ActorLocalDataSourceImpl
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasource.MovieLocalDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasourceimpl.MovieLocalDataSourceImpl
import com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasourceimpl.TvShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule() {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao) : MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun provideActorLocalDataSource(actorDao: ActorDao) : ActorLocalDataSource {
        return ActorLocalDataSourceImpl(actorDao)
    }

    @Singleton
    @Provides
    fun provideTvShowLocalDataSource(tvShowDao: TvShowDao) : TvShowLocalDataSource {
        return TvShowLocalDataSourceImpl(tvShowDao)
    }
}