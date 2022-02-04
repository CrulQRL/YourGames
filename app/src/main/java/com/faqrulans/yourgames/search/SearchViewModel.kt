package com.faqrulans.yourgames.search

import androidx.lifecycle.ViewModel
import com.faqrulans.core.domain.usecase.DeveloperUseCase
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val useCase: DeveloperUseCase
) : ViewModel() {

}
