package com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasourceimpl

import com.yuvrajpatel.tmdbdashboard.data.model.movie.Movie
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasource.MovieCacheDataSource

class MovieCacheDataSourceImpl :
    MovieCacheDataSource {

    private var movieList = ArrayList<Movie>()

    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        movieList.clear()
        movieList = ArrayList(movies)
    }
}