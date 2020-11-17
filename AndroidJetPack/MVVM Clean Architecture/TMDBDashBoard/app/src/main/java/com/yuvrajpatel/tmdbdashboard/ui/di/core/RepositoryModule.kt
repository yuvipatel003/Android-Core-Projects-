package com.yuvrajpatel.tmdbdashboard.ui.di.core

import com.yuvrajpatel.tmdbdashboard.data.repository.actor.ActorRepositoryImpl
import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource.ActorCacheDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource.ActorLocalDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.actor.datasource.ActorRemoteDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.MovieRepositoryImpl
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasource.MovieCacheDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasource.MovieLocalDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasource.MovieRemoteDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.TvShowRepositoryImpl
import com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.yuvrajpatel.tmdbdashboard.domain.repository.ActorRepository
import com.yuvrajpatel.tmdbdashboard.domain.repository.MovieRepository
import com.yuvrajpatel.tmdbdashboard.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieRemoteDataSource,
            movieLocalDataSource,
            movieCacheDataSource
        )

    }

    @Singleton
    @Provides
    fun provideTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource
    ): TvShowRepository {

        return TvShowRepositoryImpl(
            tvShowRemoteDataSource,
            tvShowLocalDataSource,
            tvShowCacheDataSource
        )

    }

    @Singleton
    @Provides
    fun provideActorRepository(
        actorRemoteDataSource: ActorRemoteDataSource,
        actorLocalDataSource: ActorLocalDataSource,
        actorCacheDataSource: ActorCacheDataSource
    ): ActorRepository {

        return ActorRepositoryImpl(
            actorRemoteDataSource,
            actorLocalDataSource,
            actorCacheDataSource
        )

    }
}