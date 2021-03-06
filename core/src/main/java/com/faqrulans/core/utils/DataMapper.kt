package com.faqrulans.core.utils

import com.faqrulans.core.data.source.local.entity.DeveloperEntity
import com.faqrulans.core.data.source.local.entity.GameEntity
import com.faqrulans.core.data.source.remote.response.DeveloperResponse
import com.faqrulans.core.data.source.remote.response.GameResponse
import com.faqrulans.core.domain.model.Developer
import com.faqrulans.core.domain.model.Game

object DataMapper {

    // Developer
    fun mapDeveloperResponsesToEntities(input: List<DeveloperResponse>): List<DeveloperEntity> =
        input.map {
            DeveloperEntity(
                id = it.id,
                name = it.name,
                gamesCount = it.gamesCount,
                imageBackground = it.imageBackground,
                isFavorite = false
            )
        }

    fun mapDeveloperEntitiesToDomain(input: List<DeveloperEntity>): List<Developer> =
        input.map {
            Developer(
                id = it.id,
                name = it.name,
                gamesCount = it.gamesCount,
                imageBackground = it.imageBackground,
                isFavorite = it.isFavorite
            )
        }

    // Game
    fun mapGameResponsesToEntities(
        input: List<GameResponse>,
        developerId: String
    ): List<GameEntity> =
        input.map {
            GameEntity(
                id = 0,
                name = it.name,
                developer = developerId,
                released = it.released,
                rating = it.rating,
                backgroundImage = it.backgroundImage
            )
        }

    fun mapGameEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                name = it.name,
                released = it.released,
                rating = it.rating,
                backgroundImage = it.backgroundImage
            )
        }
}
