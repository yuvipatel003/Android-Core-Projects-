package com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasource

import com.yuvrajpatel.tmdbdashboard.data.model.tvshow.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShows() : Response<TvShowList>
}