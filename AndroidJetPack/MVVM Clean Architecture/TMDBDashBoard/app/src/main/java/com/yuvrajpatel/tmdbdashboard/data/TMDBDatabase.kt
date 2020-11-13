package com.yuvrajpatel.tmdbdashboard.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yuvrajpatel.tmdbdashboard.data.dao.ActorDao
import com.yuvrajpatel.tmdbdashboard.data.dao.MovieDao
import com.yuvrajpatel.tmdbdashboard.data.dao.TvShowDao
import com.yuvrajpatel.tmdbdashboard.data.model.actor.Actor
import com.yuvrajpatel.tmdbdashboard.data.model.movie.Movie
import com.yuvrajpatel.tmdbdashboard.data.model.tvshow.TvShow

@Database( entities = [Movie::class, Actor::class, TvShow::class], version = 1, exportSchema = false)
abstract class TMDBDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao
    abstract fun tvShowDao() : TvShowDao
    abstract fun actorDao() : ActorDao

    companion object {

    }
}