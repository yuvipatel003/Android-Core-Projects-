package com.yuvrajpatel.tmdbdashboard.ui.di.movie

import com.yuvrajpatel.tmdbdashboard.domain.usecase.movie.GetMoviesUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.movie.UpdateMoviesUseCase
import com.yuvrajpatel.tmdbdashboard.ui.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieActivityViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ) : MovieViewModelFactory {
        return MovieViewModelFactory(getMoviesUseCase, updateMoviesUseCase)
    }
}