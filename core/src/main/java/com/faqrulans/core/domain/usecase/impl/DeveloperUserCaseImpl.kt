package com.faqrulans.core.domain.usecase.impl

import com.faqrulans.core.domain.model.Developer
import com.faqrulans.core.domain.repository.DeveloperRepository
import com.faqrulans.core.domain.usecase.DeveloperUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeveloperUserCaseImpl @Inject constructor(
    private val developerRepository: DeveloperRepository
) : DeveloperUseCase {

    override fun getDevelopers() = developerRepository.getDevelopers()

    override fun getFavoriteDeveloper(): Flow<List<Developer>> = developerRepository.getFavoriteDeveloper()

    override suspend fun updateFavoriteDeveloper(developerId: String, isFavorite: Boolean): Int =
        developerRepository.updateFavoriteDeveloper(developerId, isFavorite)
}
