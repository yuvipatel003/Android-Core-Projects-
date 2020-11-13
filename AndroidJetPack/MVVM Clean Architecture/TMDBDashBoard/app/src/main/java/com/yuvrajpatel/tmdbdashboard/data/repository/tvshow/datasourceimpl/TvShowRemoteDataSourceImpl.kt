package com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasourceimpl

import com.yuvrajpatel.tmdbdashboard.data.api.TMDBService
import com.yuvrajpatel.tmdbdashboard.data.model.tvshow.TvShowList
import com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey : String
) : TvShowRemoteDataSource {
    override suspend fun getTvShows(): Response<TvShowList> = tmdbService.getPopularTvShows(apiKey)
}