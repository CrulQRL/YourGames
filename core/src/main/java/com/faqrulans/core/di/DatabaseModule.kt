package com.faqrulans.core.di

import android.content.Context
import androidx.room.Room
import com.faqrulans.core.BuildConfig
import com.faqrulans.core.data.source.local.room.DeveloperDao
import com.faqrulans.core.data.source.local.room.GameDao
import com.faqrulans.core.data.source.local.room.YourGamesDatabase
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
interface DatabaseModule {

    companion object {
        @Singleton
        @Provides
        fun provideDatabase(context: Context): YourGamesDatabase {
            val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.PASSPHRASE.toCharArray())
            val factory = SupportFactory(passphrase)

            return Room.databaseBuilder(
                context,
                YourGamesDatabase::class.java, "YourGames.db"
            ).fallbackToDestructiveMigration()
                .openHelperFactory(factory)
                .build()
        }

        @Provides
        fun provideDeveloperDao(database: YourGamesDatabase): DeveloperDao = database.developerDao()

        @Provides
        fun provideGameDao(database: YourGamesDatabase): GameDao = database.gameDao()
    }
}
