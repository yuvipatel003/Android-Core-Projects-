package com.yuvrajpatel.tmdbdashboard.data.repository.movie

import android.util.Log
import com.yuvrajpatel.tmdbdashboard.data.model.movie.Movie
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasource.MovieCacheDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasource.MovieLocalDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.movie.datasource.MovieRemoteDataSource
import com.yuvrajpatel.tmdbdashboard.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {
    private val mTAG = MovieRepositoryImpl::class.java.simpleName

    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
        val newListOfMovies : List<Movie> = getMoviesFromAPI()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newListOfMovies)
        movieCacheDataSource.saveMoviesToCache(newListOfMovies)
        return newListOfMovies
    }

    private suspend fun getMoviesFromAPI() : List<Movie> {
        lateinit var movieList : List<Movie>
        try {
            val response = movieRemoteDataSource.getMovies()
            val body = response.body()
            if(body != null){
                movieList = body.movies
            }
        }catch (e: Exception) {
            Log.d(mTAG,"Exception : $e")
        }
        return movieList
    }

    private suspend fun getMoviesFromDB() : List<Movie> {
        lateinit var movieList : List<Movie>
        try {
            movieList = movieLocalDataSource.getMoviesFromDB()
        }catch (e: Exception) {
            Log.d(mTAG,"Exception : $e")
        }

        if(movieList.isNotEmpty()){
            return movieList
        } else {
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)
        }
        return movieList
    }


    private suspend fun getMoviesFromCache() : List<Movie> {
        lateinit var movieList : List<Movie>
        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        }catch (e: Exception) {
            Log.d(mTAG,"Exception : $e")
        }

        if(movieList.isNotEmpty()){
            return movieList
        } else {
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movieList)
        }
        return movieList
    }

}