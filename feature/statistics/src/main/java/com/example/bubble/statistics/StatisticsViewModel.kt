package com.example.bubble.statistics

import androidx.lifecycle.ViewModel
import com.example.bubble.core.utils.BubbleDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val bubbleDispatchers: BubbleDispatchers
): ViewModel() {

    
}