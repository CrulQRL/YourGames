package com.faqrulans.core.domain.usecase.Impl

import com.faqrulans.core.domain.repository.DeveloperRepository
import com.faqrulans.core.domain.usecase.DeveloperUseCase
import javax.inject.Inject

class DeveloperUserCaseImpl @Inject constructor(
    private val developerRepository: DeveloperRepository
) : DeveloperUseCase {

    override fun getDevelopers() = developerRepository.getDevelopers()

}
