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

}
