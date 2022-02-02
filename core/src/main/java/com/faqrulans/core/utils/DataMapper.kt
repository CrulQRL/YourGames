package com.faqrulans.core.utils

import com.faqrulans.core.data.Resource
import com.faqrulans.core.data.source.local.entity.DeveloperEntity
import com.faqrulans.core.data.source.remote.response.ListDeveloperResponse
import com.faqrulans.core.domain.model.Developer
import com.faqrulans.core.ui.UIState
import com.faqrulans.core.ui.developer.DeveloperUI

object DataMapper {

    fun mapResponsesToEntities(input: ListDeveloperResponse): List<DeveloperEntity> {
        val tourismList = ArrayList<DeveloperEntity>()
        input.results.map {
            val tourism = DeveloperEntity(
                id = it.id,
                name = it.name,
                gamesCount = it.gamesCount,
                imageBackground = it.imageBackground
            )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<DeveloperEntity>): List<Developer> =
        input.map {
            Developer(
                id = it.id,
                name = it.name,
                gamesCount = it.gamesCount,
                imageBackground = it.imageBackground
            )
        }

    fun mapDomainToUI(input: List<Developer>?): List<DeveloperUI> {
        if (input == null) return listOf()
        return input.map {
            DeveloperUI(
                id = it.id,
                name = it.name,
                gamesCount = it.gamesCount,
                imageBackground = it.imageBackground,
                backgroundColor = 0
            )
        }
    }

    fun <T> mapResourceToUIState(resource: Resource<*>, data: T): UIState<T> {
        return when (resource) {
            is Resource.Success -> UIState.Success(data)
            is Resource.Loading -> UIState.Loading(data)
            is Resource.Error -> UIState.Error(resource.message!!, data)
        }
    }
}
