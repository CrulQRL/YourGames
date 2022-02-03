package com.faqrulans.yourgames.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faqrulans.core.data.Resource
import com.faqrulans.core.domain.usecase.DeveloperUseCase
import com.faqrulans.core.ui.UIState
import com.faqrulans.core.ui.developer.DeveloperUI
import com.faqrulans.core.utils.DataMapper
import com.faqrulans.yourgames.R
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class HomeViewModel @Inject constructor(developerUseCase: DeveloperUseCase) : ViewModel() {

    val developers = developerUseCase.getDevelopers().map {
        if (it is Resource.Loading) {
            return@map UIState.Loading(null)
        } else if (it is Resource.Error) {
            return@map UIState.Error(R.string.resource_failed_message, null)
        }

        val developersUI = mutableListOf<DeveloperUI>()

        for (i in it.data!!.indices) {
            val color: Int = when(i % 5) {
                0 -> {
                    R.color.purple_1
                }
                1 -> {
                    R.color.pink_2
                }
                2 -> {
                    R.color.red_1
                }
                3 -> {
                    R.color.purple_2
                }
                else -> {
                    R.color.pink_1
                }
            }

            developersUI.add(DataMapper.mapDomainToUI(it.data!![i], color))
        }

        return@map UIState.Success(developersUI)
    }.asLiveData()

}
