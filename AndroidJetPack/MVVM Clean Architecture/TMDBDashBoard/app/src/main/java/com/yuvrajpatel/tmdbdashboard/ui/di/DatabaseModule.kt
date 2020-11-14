package com.yuvrajpatel.tmdbdashboard.ui.di

import android.content.Context
import androidx.room.Room
import com.yuvrajpatel.tmdbdashboard.data.TMDBDatabase
import com.yuvrajpatel.tmdbdashboard.data.dao.ActorDao
import com.yuvrajpatel.tmdbdashboard.data.dao.MovieDao
import com.yuvrajpatel.tmdbdashboard.data.dao.TvShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context) : TMDBDatabase {
        return Room.databaseBuilder(context,TMDBDatabase::class.java,"tmdb_database").build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase) : MovieDao{
        return tmdbDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideActorDao(tmdbDatabase: TMDBDatabase) : ActorDao{
        return tmdbDatabase.actorDao()
    }

    @Singleton
    @Provides
    fun provideTvShowDao(tmdbDatabase: TMDBDatabase) :TvShowDao{
        return tmdbDatabase.tvShowDao()
    }
}