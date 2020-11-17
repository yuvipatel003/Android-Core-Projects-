package com.yuvrajpatel.tmdbdashboard.ui.di.core

import com.yuvrajpatel.tmdbdashboard.domain.repository.ActorRepository
import com.yuvrajpatel.tmdbdashboard.domain.repository.MovieRepository
import com.yuvrajpatel.tmdbdashboard.domain.repository.TvShowRepository
import com.yuvrajpatel.tmdbdashboard.domain.usecase.actor.GetActorUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.actor.UpdateActorUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.movie.GetMoviesUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.movie.UpdateMoviesUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.tvshow.GetTvShowUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.tvshow.UpdateTvShowUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository) : GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideUpdateMovieUseCase(movieRepository: MovieRepository) : UpdateMoviesUseCase {
        return UpdateMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideGetActorUseCase(actorRepository: ActorRepository) : GetActorUseCase {
        return GetActorUseCase(actorRepository)
    }

    @Provides
    fun provideUpdateActorUseCase(actorRepository: ActorRepository) : UpdateActorUseCase {
        return UpdateActorUseCase(actorRepository)
    }

    @Provides
    fun provideGetTvShowUseCase(tvShowRepository: TvShowRepository) : GetTvShowUseCase {
        return GetTvShowUseCase(tvShowRepository)
    }

    @Provides
    fun provideUpdateTvShowUseCase(tvShowRepository: TvShowRepository) : UpdateTvShowUseCase {
        return UpdateTvShowUseCase(tvShowRepository)
    }

}