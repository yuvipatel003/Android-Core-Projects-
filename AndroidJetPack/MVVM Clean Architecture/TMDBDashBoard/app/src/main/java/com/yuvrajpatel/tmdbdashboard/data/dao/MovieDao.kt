package com.yuvrajpatel.tmdbdashboard.data.dao

import androidx.room.*
import com.yuvrajpatel.tmdbdashboard.data.model.movie.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies : List<Movie>)

    @Query("SELECT * FROM popular_movie")
    suspend fun getMovies() : List<Movie>

    @Query("DELETE FROM popular_movie")
    suspend fun deleteAllMovies()
}