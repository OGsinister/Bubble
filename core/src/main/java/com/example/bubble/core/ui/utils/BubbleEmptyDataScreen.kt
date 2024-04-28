package com.example.bubble.core.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bubble.core.R
import com.example.bubble.core.ui.theme.BubbleTheme

@Composable
fun BubbleEmptyDataScreen(
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_not_found),
            contentDescription = null,
            modifier = Modifier
                .size(256.dp)
        )
        Text(
            text = stringResource(id = R.string.not_found),
            style = BubbleTheme.typography.heading,
            color = BubbleTheme.colors.errorColor,
            modifier = Modifier.padding(10.dp)
        )
    }
}