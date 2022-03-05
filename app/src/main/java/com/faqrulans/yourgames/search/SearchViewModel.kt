package com.faqrulans.yourgames.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faqrulans.core.domain.usecase.DeveloperUseCase
import com.faqrulans.yourgames.utils.UIStateMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SearchViewModel @Inject constructor(
    private val useCase: DeveloperUseCase
) : ViewModel() {

    val query = MutableStateFlow("")

    val developers = query
        .debounce(timeoutMillis = 500)
        .distinctUntilChanged()
        .flatMapLatest {
            useCase.searchDeveloperByName(it).map { developers ->
                UIStateMapper.mapDeveloperDomainToUI(developers)
            }
        }.asLiveData()

}
