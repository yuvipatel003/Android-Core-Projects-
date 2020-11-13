package com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasource

import com.yuvrajpatel.tmdbdashboard.data.model.movie.Movie

interface MovieCacheDataSource {
    suspend fun getMoviesFromCache() : List<Movie>
    suspend fun saveMoviesToCache(movies : List<Movie>)
}