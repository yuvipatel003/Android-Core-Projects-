package com.yuvrajpatel.tmdbdashboard.ui.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.yuvrajpatel.tmdbdashboard.domain.usecase.tvshow.GetTvShowUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.tvshow.UpdateTvShowUseCase

class TvShowViewModelFactory(
    private val getTvShowUseCase: GetTvShowUseCase,
    private val updateTvShowUseCase: UpdateTvShowUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvShowViewModel(
            getTvShowUseCase,
            updateTvShowUseCase
        ) as T
    }
}