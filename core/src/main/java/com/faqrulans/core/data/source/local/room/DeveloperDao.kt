package com.faqrulans.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import com.faqrulans.core.data.source.local.entity.DeveloperEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeveloperDao {

    @Query("SELECT * FROM developer")
    fun getAllTourism(): Flow<List<DeveloperEntity>>

}
