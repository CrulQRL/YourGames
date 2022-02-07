package com.faqrulans.core.domain.repository

import com.faqrulans.core.data.Resource
import com.faqrulans.core.domain.model.Developer
import kotlinx.coroutines.flow.Flow

interface DeveloperRepository {

    fun getDevelopers(): Flow<Resource<List<Developer>>>

    fun getFavoriteDeveloper(): Flow<List<Developer>>

    fun searchDeveloperByName(query: String): Flow<List<Developer>>

    suspend fun updateFavoriteDeveloper(developerId: String, isFavorite: Boolean): Int
}
