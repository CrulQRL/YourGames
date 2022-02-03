package com.faqrulans.core.data

import com.faqrulans.core.data.source.local.LocalDataSource
import com.faqrulans.core.data.source.remote.RemoteDataSource
import com.faqrulans.core.data.source.remote.network.ApiResponse
import com.faqrulans.core.data.source.remote.response.GameResponse
import com.faqrulans.core.domain.model.Game
import com.faqrulans.core.domain.repository.GameRepository
import com.faqrulans.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : GameRepository {

    override fun getGames(developerId: String): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getGamesByDeveloperId(developerId).map {
                    DataMapper.mapGameEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> {
                return remoteDataSource.getGames(developerId)
            }

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapGameResponsesToEntities(data, developerId)
                localDataSource.insertGames(gameList)
            }
        }.asFlow()
}
