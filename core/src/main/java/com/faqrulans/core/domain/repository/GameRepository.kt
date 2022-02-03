package com.faqrulans.core.domain.repository

import com.faqrulans.core.data.Resource
import com.faqrulans.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames(developerId: String): Flow<Resource<List<Game>>>
}
