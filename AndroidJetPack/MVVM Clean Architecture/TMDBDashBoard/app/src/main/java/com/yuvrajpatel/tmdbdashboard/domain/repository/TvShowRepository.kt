package com.yuvrajpatel.tmdbdashboard.domain.repository

import com.yuvrajpatel.tmdbdashboard.data.model.tvshow.TvShow

interface TvShowRepository {

    suspend fun getTvShows(): List<TvShow>?
    suspend fun updateTvShows(): List<TvShow>?
}