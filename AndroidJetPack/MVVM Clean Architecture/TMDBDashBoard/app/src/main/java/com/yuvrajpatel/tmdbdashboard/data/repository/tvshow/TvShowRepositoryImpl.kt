package com.yuvrajpatel.tmdbdashboard.data.repository.tvshow

import android.util.Log
import com.yuvrajpatel.tmdbdashboard.data.model.tvshow.TvShow
import com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.yuvrajpatel.tmdbdashboard.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.yuvrajpatel.tmdbdashboard.domain.repository.TvShowRepository
import java.lang.Exception

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
) : TvShowRepository {

    private val mTAG = TvShowRepositoryImpl::class.java.simpleName

    override suspend fun getTvShows(): List<TvShow>? {
        return getTvShowsFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {
        val newTvShowList = getTvShowsFromAPI()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDB(newTvShowList)
        tvShowCacheDataSource.saveTvShowsToCache(newTvShowList)
        return newTvShowList
    }

    private suspend fun getTvShowsFromAPI() : List<TvShow> {
        lateinit var tvShowList : List<TvShow>
        try {
            val response = tvShowRemoteDataSource.getTvShows()
            val body = response.body()
            if(body!=null){
                tvShowList = body.tvshows
            }
        }catch (e : Exception){
            Log.d(mTAG,"Exception : $e")
        }
        return tvShowList
    }

    private suspend fun getTvShowsFromDB() : List<TvShow>{
        lateinit var tvShowList : List<TvShow>
        try {
            tvShowList = tvShowLocalDataSource.getTvShowsFromDB()
        }catch (e : Exception){
            Log.d(mTAG,"Exception : $e")
        }

        if(tvShowList.isNotEmpty()){
            return tvShowList
        }else {
            tvShowList = getTvShowsFromAPI()
            tvShowLocalDataSource.saveTvShowsToDB(tvShowList)
        }
        return tvShowList
    }

    private suspend fun getTvShowsFromCache() : List<TvShow>{
        lateinit var tvShowList : List<TvShow>
        try {
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache()
        }catch (e : Exception){
            Log.d(mTAG,"Exception : $e")
        }

        if(tvShowList.isNotEmpty()){
            return tvShowList
        }else {
            tvShowList = getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowList)
        }
        return tvShowList
    }
}