package com.example.bubble.core.ui.utils

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BubbleBottomSheet(
    onDismiss: () -> Unit,
    sheetState: SheetState,
    content: @Composable()() -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ){
        content()
    }
}