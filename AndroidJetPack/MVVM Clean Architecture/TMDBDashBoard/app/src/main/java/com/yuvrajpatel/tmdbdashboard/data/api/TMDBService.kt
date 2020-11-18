package com.yuvrajpatel.tmdbdashboard.data.api

import com.yuvrajpatel.tmdbdashboard.data.model.movie.MovieList
import com.yuvrajpatel.tmdbdashboard.data.model.actor.ActorList
import com.yuvrajpatel.tmdbdashboard.data.model.tvshow.TvShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey : String) : Response<MovieList>

    @GET("person/popular")
    suspend fun getPopularActors(@Query("api_key") apiKey : String) : Response<ActorList>

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("api_key") apiKey : String) : Response<TvShowList>
}