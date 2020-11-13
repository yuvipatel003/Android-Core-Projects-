package com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasource

import com.yuvrajpatel.tmdbdashboard.data.model.tvshow.TvShow
import com.yuvrajpatel.tmdbdashboard.data.model.tvshow.TvShowList

interface TvShowLocalDataSource {
    suspend fun getTvShowsFromDB() : List<TvShow>
    suspend fun saveTvShowsToDB(tvShows: List<TvShow>)
    suspend fun clearAll()
}