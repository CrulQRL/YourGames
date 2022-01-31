package com.faqrulans.core.data.source.local

import com.faqrulans.core.data.source.local.entity.DeveloperEntity
import com.faqrulans.core.data.source.local.room.DeveloperDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val developerDao: DeveloperDao) {

    fun getDevelopers(): Flow<List<DeveloperEntity>> = developerDao.getAllDeveloper()

    suspend fun insertDevelopers(developers: List<DeveloperEntity>) = developerDao.insert(developers)

}
