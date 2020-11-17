package com.yuvrajpatel.tmdbdashboard.ui.di.tvshow

import com.yuvrajpatel.tmdbdashboard.domain.usecase.tvshow.GetTvShowUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.tvshow.UpdateTvShowUseCase
import com.yuvrajpatel.tmdbdashboard.ui.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {

    @TvShowScope
    @Provides
    fun provideTvShowActivityViewModelFactory(
        getTvShowUseCase: GetTvShowUseCase,
        updateTvShowUseCase: UpdateTvShowUseCase
    ) : TvShowViewModelFactory {
        return TvShowViewModelFactory(getTvShowUseCase, updateTvShowUseCase)
    }
}