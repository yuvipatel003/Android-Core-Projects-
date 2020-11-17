package com.yuvrajpatel.tmdbdashboard.ui.di.core

import com.yuvrajpatel.tmdbdashboard.data.api.TMDBService
import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource.ActorRemoteDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasourceimpl.ActorRemoteDataSourceImpl
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasource.MovieRemoteDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasourceimpl.MovieRemoteDataSourceImpl
import com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasourceimpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey : String) {

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(tmdbService,apiKey)
    }

    @Singleton
    @Provides
    fun provideActorRemoteDataSource(tmdbService: TMDBService): ActorRemoteDataSource {
        return ActorRemoteDataSourceImpl(tmdbService,apiKey)
    }

    @Singleton
    @Provides
    fun provideTvShowRemoteDataSource(tmdbService: TMDBService): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImpl(tmdbService,apiKey)
    }
}