package com.yuvrajpatel.tmdbdashboard.data.model.actor

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_actors")
data class Actor(
    @PrimaryKey
    val id: Int,
    val name: String,
    val popularity: Double,
    val profile_path: String
)