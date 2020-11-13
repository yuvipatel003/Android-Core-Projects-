package com.yuvrajpatel.tmdbdashboard.data.dao

import androidx.room.*
import com.yuvrajpatel.tmdbdashboard.data.model.tvshow.TvShow

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShows(movies : List<TvShow>)

    @Query("SELECT * FROM popular_tvshows")
    suspend fun getTvShows() : List<TvShow>

    @Query("DELETE FROM popular_tvshows")
    suspend fun deleteAllTvShows()
}