package com.faqrulans.yourgames.utils

import com.faqrulans.core.domain.model.Developer
import com.faqrulans.core.domain.model.Game
import com.faqrulans.core.ui.developer.DeveloperUI
import com.faqrulans.yourgames.detail.GameUI

object UIStateMapper {

    fun mapDeveloperDomainToUI(input: Developer, backgroundColor: Int): DeveloperUI =
        DeveloperUI(
            id = input.id,
            name = input.name,
            gamesCount = input.gamesCount,
            imageBackground = input.imageBackground,
            backgroundColor = backgroundColor
        )

    fun mapGameDomainToUI(input: List<Game>): List<GameUI> =
        input.map {
            GameUI(
                id = it.id,
                name = it.name,
                released = it.released,
                rating = it.rating,
                backgroundImage = it.backgroundImage
            )
        }

}
