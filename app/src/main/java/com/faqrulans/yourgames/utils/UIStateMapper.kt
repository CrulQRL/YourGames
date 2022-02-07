package com.faqrulans.yourgames.utils

import com.faqrulans.core.domain.model.Developer
import com.faqrulans.core.domain.model.Game
import com.faqrulans.core.ui.developer.DeveloperUI
import com.faqrulans.yourgames.R
import com.faqrulans.yourgames.detail.GameUI

object UIStateMapper {

    fun mapDeveloperDomainToUI(input: List<Developer>): List<DeveloperUI> {
        val developersUI = mutableListOf<DeveloperUI>()

        for (i in input.indices) {
            val color: Int = when(i % 5) {
                0 -> {
                    R.color.purple_1
                }
                1 -> {
                    R.color.pink_2
                }
                2 -> {
                    R.color.red_1
                }
                3 -> {
                    R.color.purple_2
                }
                else -> {
                    R.color.pink_1
                }
            }

            developersUI.add(mapDeveloperDomainToUI(input[i], color))
        }

        return developersUI
    }

    private fun mapDeveloperDomainToUI(input: Developer, backgroundColor: Int): DeveloperUI =
        DeveloperUI(
            id = input.id,
            name = input.name,
            gamesCount = input.gamesCount,
            imageBackground = input.imageBackground,
            backgroundColor = backgroundColor,
            isFavorite = input.isFavorite
        )

    fun mapGameDomainToUI(input: List<Game>): List<GameUI> =
        input.map {
            GameUI(
                name = it.name,
                released = it.released,
                rating = it.rating.toString(),
                backgroundImage = it.backgroundImage
            )
        }

}
