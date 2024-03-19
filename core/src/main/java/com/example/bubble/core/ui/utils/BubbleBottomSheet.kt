package com.example.bubble.core.ui.utils

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import com.example.bubble.core.ui.theme.BubbleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BubbleBottomSheet(
    onDismiss: () -> Unit,
    sheetState: SheetState,
    content: @Composable()() -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = BubbleTheme.colors.backgroundGradientColorDark2,
        windowInsets = WindowInsets(0,0,0)
    ){
        content()
    }
}