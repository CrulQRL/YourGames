package com.faqrulans.yourgames.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faqrulans.core.domain.usecase.DeveloperUseCase
import javax.inject.Inject


class HomeViewModel @Inject constructor(developerUseCase: DeveloperUseCase) : ViewModel() {

    val developers = developerUseCase.getDevelopers().asLiveData()

}
