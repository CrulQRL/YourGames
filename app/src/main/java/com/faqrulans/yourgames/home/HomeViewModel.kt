package com.faqrulans.yourgames.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faqrulans.core.data.Resource
import com.faqrulans.core.domain.usecase.DeveloperUseCase
import com.faqrulans.core.ui.UIState
import com.faqrulans.yourgames.R
import com.faqrulans.yourgames.utils.UIStateMapper
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class HomeViewModel @Inject constructor(developerUseCase: DeveloperUseCase) : ViewModel() {

    val developers = developerUseCase.getDevelopers().map {
        if (it is Resource.Loading) {
            return@map UIState.Loading(null)
        } else if (it is Resource.Error) {
            return@map UIState.Error(R.string.resource_failed_message, null)
        }

        return@map UIState.Success(UIStateMapper.mapDeveloperDomainToUI(it.data!!))
    }.asLiveData()

}
