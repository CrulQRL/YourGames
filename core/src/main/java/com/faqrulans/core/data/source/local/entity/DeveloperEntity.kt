package com.faqrulans.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "developer")
data class DeveloperEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
)
