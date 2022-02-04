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
        val developersUI = mutableListOf<DeveloperUI>()

        for (i in it.indices) {
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

            developersUI.add(UIStateMapper.mapDeveloperDomainToUI(it[i], color))
        }

        return@map developersUI
    }.asLiveData()

}
