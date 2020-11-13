package com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasourceimpl

import com.yuvrajpatel.tmdbdashboard.data.api.TMDBService
import com.yuvrajpatel.tmdbdashboard.data.model.movie.MovieList
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey : String
) : MovieRemoteDataSource {

override suspend fun getMovies(): Response<MovieList> = tmdbService.getPopularMovies(apiKey)
}