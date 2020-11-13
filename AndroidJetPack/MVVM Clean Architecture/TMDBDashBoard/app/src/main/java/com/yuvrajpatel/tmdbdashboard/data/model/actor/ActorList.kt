package com.yuvrajpatel.tmdbdashboard.data.model.actor

data class ActorList(
    val page: Int,
    val actors: List<Actor>,
    val total_pages: Int,
    val total_results: Int
)