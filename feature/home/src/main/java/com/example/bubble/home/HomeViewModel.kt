package com.example.bubble.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.home.model.HomeEvents
import com.example.bubble.home.model.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bubbleDispatchers: BubbleDispatchers
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.DefaultState)
    internal var state = _state.asStateFlow()

    internal fun event(event: HomeEvents){
        when(event){
            is HomeEvents.RunFocus -> {
                _state.value = HomeState.FocusRunning
            }

            HomeEvents.StopFocus -> {
                _state.value = HomeState.DefaultState
            }
        }
    }
}
