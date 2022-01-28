package com.faqrulans.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faqrulans.core.data.source.local.entity.DeveloperEntity

@Database(entities = [DeveloperEntity::class], version = 1, exportSchema = false)
abstract class YourGamesDatabase : RoomDatabase() {

    abstract fun developerDao(): DeveloperDao

}
