package com.faqrulans.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "developer")
data class DeveloperEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "games_count")
    val gamesCount: Int,
    @ColumnInfo(name = "image_background")
    val imageBackground: String,
    @ColumnInfo(name = "is_favorite", defaultValue = "false")
    val isFavorite: Boolean
)
