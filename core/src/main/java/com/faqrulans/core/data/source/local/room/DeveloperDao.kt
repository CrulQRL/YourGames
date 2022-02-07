package com.faqrulans.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.faqrulans.core.data.source.local.entity.DeveloperEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeveloperDao {

    @Query("SELECT * FROM developer")
    fun getAllDeveloper(): Flow<List<DeveloperEntity>>

    @Insert
    suspend fun insert(developers: List<DeveloperEntity>)

    @Query("UPDATE developer set is_favorite = :isFavorite WHERE id = :developerId")
    suspend fun updateFavorite(developerId: String, isFavorite: Boolean): Int

    @Query("SELECT * FROM developer WHERE is_favorite = 1")
    fun getFavoriteDeveloper(): Flow<List<DeveloperEntity>>

    @Query("SELECT * FROM developer WHERE name LIKE '%'||:query||'%'")
    fun searchByName(query: String): Flow<List<DeveloperEntity>>

}
