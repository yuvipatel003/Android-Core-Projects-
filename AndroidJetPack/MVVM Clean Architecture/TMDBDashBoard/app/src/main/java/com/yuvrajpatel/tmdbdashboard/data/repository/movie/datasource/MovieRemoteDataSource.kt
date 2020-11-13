package com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasource

import com.yuvrajpatel.tmdbdashboard.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies() : Response<MovieList>
}