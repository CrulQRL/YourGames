package com.faqrulans.core.utils

import com.faqrulans.core.data.source.local.entity.DeveloperEntity
import com.faqrulans.core.data.source.remote.response.DeveloperResponse
import com.faqrulans.core.domain.model.Developer
import com.faqrulans.core.ui.developer.DeveloperUI

object DataMapper {

    fun mapResponsesToEntities(input: List<DeveloperResponse>): List<DeveloperEntity> {
        val tourismList = ArrayList<DeveloperEntity>()
        input.map {
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

    fun mapDomainToUI(input: Developer, backgroundColor: Int): DeveloperUI =
        DeveloperUI(
            id = input.id,
            name = input.name,
            gamesCount = input.gamesCount,
            imageBackground = input.imageBackground,
            backgroundColor = backgroundColor
        )
}
