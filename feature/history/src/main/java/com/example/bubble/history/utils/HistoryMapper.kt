package com.example.bubble.history.utils

import androidx.compose.ui.graphics.Color
import com.example.bubble.core.ui.utils.TagUI
import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.domain.model.History
import com.example.bubble.domain.model.Tag
import com.example.bubble.history.model.HistoryState

fun Tag.toTagUI(): TagUI {
    return TagUI(
        id = id,
        name = tagName,
        color = Color(tagColor),
        icon = tagIcon
    )
}

internal fun DatabaseResource<List<History>>.toHistoryState(): HistoryState {
    return when(this){
        is DatabaseResource.Default -> HistoryState.DefaultState
        is DatabaseResource.Empty -> HistoryState.EmptyDataState(message = message)
        is DatabaseResource.Error -> HistoryState.ErrorState(message)
        is DatabaseResource.LoadedData -> HistoryState.LoadedDataState(loadedData)
        is DatabaseResource.Loading -> HistoryState.IsLoadingState
    }
}