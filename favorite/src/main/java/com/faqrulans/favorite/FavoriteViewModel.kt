package com.faqrulans.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faqrulans.core.domain.usecase.DeveloperUseCase
import com.faqrulans.core.ui.developer.DeveloperUI
import com.faqrulans.yourgames.R
import com.faqrulans.yourgames.utils.UIStateMapper
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    developerUseCase: DeveloperUseCase
) : ViewModel() {

    val developers = developerUseCase.getFavoriteDeveloper().map {
        UIStateMapper.mapDeveloperDomainToUI(it)
    }.asLiveData()

}
