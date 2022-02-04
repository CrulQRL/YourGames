package com.faqrulans.core.data.source.local

import com.faqrulans.core.data.source.local.entity.DeveloperEntity
import com.faqrulans.core.data.source.local.entity.GameEntity
import com.faqrulans.core.data.source.local.room.DeveloperDao
import com.faqrulans.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val developerDao: DeveloperDao,
    private val gameDao: GameDao
) {

    fun getDevelopers(): Flow<List<DeveloperEntity>> =
        developerDao.getAllDeveloper()

    fun getGamesByDeveloperId(developerId: String): Flow<List<GameEntity>> =
        gameDao.getGamesByDeveloperId(developerId)

    fun getFavoriteDeveloper() =
        developerDao.getFavoriteDeveloper()

    suspend fun insertDevelopers(developers: List<DeveloperEntity>) =
        developerDao.insert(developers)

    suspend fun insertGames(games: List<GameEntity>) =
        gameDao.insert(games)

    suspend fun updateFavorite(developerId: String, isFavorite: Boolean): Int =
        developerDao.updateFavorite(developerId, isFavorite)

}
