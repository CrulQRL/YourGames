package com.faqrulans.yourgames.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faqrulans.core.data.Resource
import com.faqrulans.core.domain.usecase.GameUseCase
import com.faqrulans.core.ui.UIState
import com.faqrulans.yourgames.R
import com.faqrulans.yourgames.utils.UIStateMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeveloperDetailViewModel @Inject constructor(
    private val gameUseCase: GameUseCase
) : ViewModel() {


    private val _games = MutableLiveData<UIState<List<GameUI>>>()
    val games: LiveData<UIState<List<GameUI>>> = _games

    fun getGames(developerId: String) {
        viewModelScope.launch {
            gameUseCase.getGames(developerId).collect {
                val state = when (it) {
                    is Resource.Loading -> {
                        UIState.Loading<List<GameUI>>(null)
                    }
                    is Resource.Success -> {
                        UIState.Success(UIStateMapper.mapGameDomainToUI(it.data!!))
                    }
                    else -> {
                        UIState.Error<List<GameUI>>(R.string.resource_failed_message, null)
                    }
                }
                _games.postValue(state)
            }
        }
    }

}
