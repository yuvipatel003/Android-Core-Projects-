package com.yuvrajpatel.tmdbdashboard.ui.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.yuvrajpatel.tmdbdashboard.domain.usecase.movie.GetMoviesUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.movie.UpdateMoviesUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.tvshow.GetTvShowUseCase
import com.yuvrajpatel.tmdbdashboard.domain.usecase.tvshow.UpdateTvShowUseCase

class TvShowViewModel(
    private val getTvShowUseCase: GetTvShowUseCase,
    private val updateTvShowUseCase: UpdateTvShowUseCase
) : ViewModel() {

    fun getTvShows() = liveData {
        val movieList = getTvShowUseCase.execute()
        emit(movieList)
    }

    fun updateTvShows() = liveData {
        val movieList = updateTvShowUseCase.execute()
        emit(movieList)
    }
}