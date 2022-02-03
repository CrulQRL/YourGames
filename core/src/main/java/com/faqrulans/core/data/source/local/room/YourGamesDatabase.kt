package com.faqrulans.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faqrulans.core.data.source.local.entity.DeveloperEntity
import com.faqrulans.core.data.source.local.entity.GameEntity

@Database(entities = [DeveloperEntity::class, GameEntity::class],
    version = 2,
    exportSchema = false)
abstract class YourGamesDatabase : RoomDatabase() {

    abstract fun developerDao(): DeveloperDao

    abstract fun gameDao(): GameDao

}
