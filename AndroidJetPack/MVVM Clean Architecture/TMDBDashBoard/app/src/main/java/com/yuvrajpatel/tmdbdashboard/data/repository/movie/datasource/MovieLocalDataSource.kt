package com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasource

import com.yuvrajpatel.tmdbdashboard.data.model.movie.Movie

interface MovieLocalDataSource {
    suspend fun getMoviesFromDB() : List<Movie>
    suspend fun saveMoviesToDB(movies : List<Movie>)
    suspend fun clearAll()
}