package com.faqrulans.core.domain.usecase

import com.faqrulans.core.data.Resource
import com.faqrulans.core.domain.Developer
import kotlinx.coroutines.flow.Flow

interface DeveloperUseCase {

    fun getDevelopers() : Flow<Resource<List<Developer>>>

}
