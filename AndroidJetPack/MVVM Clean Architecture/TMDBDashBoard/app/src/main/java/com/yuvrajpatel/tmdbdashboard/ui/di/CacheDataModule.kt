package com.yuvrajpatel.tmdbdashboard.ui.di

import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource.ActorCacheDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasourceimpl.ActorCacheDataSourceImpl
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasource.MovieCacheDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasourceimpl.MovieCacheDataSourceImpl
import com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasourceimpl.TvShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun provideMovieCacheDataSource():MovieCacheDataSource {
        return MovieCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideActorCacheDataSource(): ActorCacheDataSource {
        return ActorCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideTvShowCacheDataSource(): TvShowCacheDataSource {
        return TvShowCacheDataSourceImpl()
    }
}