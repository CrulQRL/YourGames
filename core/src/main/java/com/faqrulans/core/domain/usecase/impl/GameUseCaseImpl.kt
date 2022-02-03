package com.faqrulans.core.domain.usecase.impl

import com.faqrulans.core.data.Resource
import com.faqrulans.core.domain.model.Game
import com.faqrulans.core.domain.repository.GameRepository
import com.faqrulans.core.domain.usecase.GameUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameUseCaseImpl @Inject constructor(
    private val gameRepository: GameRepository
) : GameUseCase {

    override fun getGames(developerId: String): Flow<Resource<List<Game>>> =
        gameRepository.getGames(developerId)
}
