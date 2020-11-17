package com.yuvrajpatel.tmdbdashboard.ui

import android.app.Application
import com.yuvrajpatel.tmdbdashboard.BuildConfig
import com.yuvrajpatel.tmdbdashboard.ui.di.Injector
import com.yuvrajpatel.tmdbdashboard.ui.di.actor.ActorSubComponent
import com.yuvrajpatel.tmdbdashboard.ui.di.core.*
import com.yuvrajpatel.tmdbdashboard.ui.di.movie.MovieSubComponent
import com.yuvrajpatel.tmdbdashboard.ui.di.tvshow.TvShowSubComponent

class App : Application(), Injector {

    private lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()
    }


    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }

    override fun createActorSubComponent(): ActorSubComponent {
        return appComponent.actorSubComponent().create()
    }

    override fun createTvShowSubComponent(): TvShowSubComponent {
        return appComponent.tvShowSubComponent().create()
    }
}