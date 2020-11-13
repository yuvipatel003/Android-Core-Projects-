package com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasource

import com.yuvrajpatel.tmdbdashboard.data.model.tvshow.TvShow
import com.yuvrajpatel.tmdbdashboard.data.model.tvshow.TvShowList

interface TvShowCacheDataSource {
    suspend fun getTvShowsFromCache() : List<TvShow>
    suspend fun saveTvShowsToCache(tvShows: List<TvShow>)
}