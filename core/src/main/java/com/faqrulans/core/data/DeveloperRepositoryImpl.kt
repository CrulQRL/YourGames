package com.faqrulans.core.data

import com.faqrulans.core.data.source.local.LocalDataSource
import com.faqrulans.core.data.source.remote.RemoteDataSource
import com.faqrulans.core.data.source.remote.network.ApiResponse
import com.faqrulans.core.data.source.remote.response.DeveloperResponse
import com.faqrulans.core.domain.model.Developer
import com.faqrulans.core.domain.repository.DeveloperRepository
import com.faqrulans.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeveloperRepositoryImpl @Inject constructor (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : DeveloperRepository {

    override fun getDevelopers(): Flow<Resource<List<Developer>>> =
        object : NetworkBoundResource<List<Developer>, List<DeveloperResponse>>() {
            override fun loadFromDB(): Flow<List<Developer>> {
                return localDataSource.getDevelopers().map {
                    DataMapper.mapDeveloperEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Developer>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<DeveloperResponse>>> {
                return remoteDataSource.getAllDeveloper()
            }

            override suspend fun saveCallResult(data: List<DeveloperResponse>) {
                val developerList = DataMapper.mapDeveloperResponsesToEntities(data)
                localDataSource.insertDevelopers(developerList)
            }
        }.asFlow()

    override fun getFavoriteDeveloper(): Flow<List<Developer>> {
        return localDataSource.getFavoriteDeveloper().map {
            DataMapper.mapDeveloperEntitiesToDomain(it)
        }
    }

    override suspend fun updateFavoriteDeveloper(developerId: String, isFavorite: Boolean): Int {
        return localDataSource.updateFavorite(developerId, isFavorite)
    }
}
