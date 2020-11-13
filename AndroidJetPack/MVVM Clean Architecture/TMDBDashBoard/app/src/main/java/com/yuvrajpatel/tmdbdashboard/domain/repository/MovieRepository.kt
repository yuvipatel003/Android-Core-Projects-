package com.yuvrajpatel.tmdbdashboard.domain.repository

import com.yuvrajpatel.tmdbdashboard.data.model.movie.Movie

interface MovieRepository {

    suspend fun getMovies(): List<Movie>?
    suspend fun updateMovies(): List<Movie>?
}