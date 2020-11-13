package com.yuvrajpatel.tmdbdashboard.domain.usecase.tvshow

import com.yuvrajpatel.tmdbdashboard.data.model.tvshow.TvShow
import com.yuvrajpatel.tmdbdashboard.domain.repository.TvShowRepository

class UpdateTvShowUseCase(private val tvShowRepository: TvShowRepository) {

    suspend fun execute(): List<TvShow>? = tvShowRepository.updateTvShows()

}