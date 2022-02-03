package com.faqrulans.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "developer")
    val developer: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "released")
    val released: String?,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "backgroundImage")
    val backgroundImage: String?
)
