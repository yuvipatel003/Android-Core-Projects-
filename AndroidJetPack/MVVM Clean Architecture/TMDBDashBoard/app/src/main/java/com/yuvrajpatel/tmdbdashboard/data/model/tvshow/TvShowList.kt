package com.yuvrajpatel.tmdbdashboard.data.model.tvshow

data class TvShowList(
    val page: Int,
    val tvshows: List<TvShow>,
    val total_pages: Int,
    val total_results: Int
)