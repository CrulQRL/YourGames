package com.faqrulans.core.domain.usecase

import com.faqrulans.core.data.Resource
import com.faqrulans.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {

    fun getGames(developerId: String) : Flow<Resource<List<Game>>>

}
