package com.faqrulans.yourgames.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faqrulans.core.domain.usecase.DeveloperUseCase
import com.faqrulans.core.utils.DataMapper
import com.faqrulans.yourgames.R
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class HomeViewModel @Inject constructor(developerUseCase: DeveloperUseCase) : ViewModel() {

    val developers = developerUseCase.getDevelopers().map {
        val developersUI = DataMapper.mapDomainToUI(it.data)

        for (i in developersUI.indices) {
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

            developersUI[i].backgroundColor = color
        }

        DataMapper.mapResourceToUIState(it, developersUI)
    }.asLiveData()

}
