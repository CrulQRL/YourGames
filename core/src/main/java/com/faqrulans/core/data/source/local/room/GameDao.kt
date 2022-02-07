package com.faqrulans.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.faqrulans.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game WHERE developer = :developerId")
    fun getGamesByDeveloperId(developerId: String): Flow<List<GameEntity>>

    @Insert
    suspend fun insert(games: List<GameEntity>)
}
